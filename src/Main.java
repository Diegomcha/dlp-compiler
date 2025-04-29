import ast.Program;
import codegeneration.ExecuteCGVisitor;
import codegeneration.OffsetVisitor;
import codegeneration.util.CodeGenerator;
import errorhandler.ErrorHandler;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorView;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.CmmLexer;
import parser.CmmParser;
import semantic.IdentificationVisitor;
import semantic.TypeCheckingVisitor;

public class Main {

    public static void main(String... args) throws Exception {
        if (args.length < 2) {
            System.err.println("Please, pass me the input and output filenames.");
            return;
        }

        /// Lexical Analysis

        CharStream input = CharStreams.fromFileName(args[0]);
        CmmLexer lexer = new CmmLexer(input);

        /// Syntactic Analysis

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CmmParser parser = new CmmParser(tokens);
        Program ast = parser.program().ast;

        if (parser.getNumberOfSyntaxErrors() > 0)
            return;

        /// Semantic Analysis
        ast.accept(new IdentificationVisitor(), null);
        ast.accept(new TypeCheckingVisitor(), null);

        if (ErrorHandler.getInstance().anyError()) {
            ErrorHandler.getInstance().showErrors(System.err);
            return;
        }

        /// Code generation
        ast.accept(new OffsetVisitor(), null);
        try (CodeGenerator cg = new CodeGenerator(args[0], args[1])) {
            ast.accept(new ExecuteCGVisitor(cg), null);
        }

        // Introspector
        IntrospectorModel model = new IntrospectorModel("Program", ast);
        new IntrospectorView("Introspector", model);
    }
}
