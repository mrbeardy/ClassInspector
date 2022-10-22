import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.util.Arrays;

public class ClassInspector {
    private Class classToInspect;

    public ClassInspector(Class classToInspect) {
        this.classToInspect = classToInspect;
    }

    public void inspect() {
        StringBuilderPlus sb = new StringBuilderPlus();
        Annotation[] classAnnotations = classToInspect.getDeclaredAnnotations();

        
        if (classAnnotations.length > 0) {
            sb.appendln("// Annotations");
            appendAnnotations(sb, classToInspect.getDeclaredAnnotations());
        }

        sb.appendln("%s {", classToInspect.toGenericString());

        sb.push();
        {
            sb.appendln("// Fields");
                for (Field field : Arrays.asList(classToInspect.getDeclaredFields())) {
                    appendAnnotations(sb, field.getDeclaredAnnotations());
        
                    sb.appendln(field.toGenericString() + ";");
                }
            sb.appendln();

            sb.appendln("// Constructors");
                appendExecutables(sb, classToInspect.getDeclaredMethods());
            sb.appendln();

            sb.appendln("// Methods");
                appendExecutables(sb, classToInspect.getDeclaredConstructors());
            sb.appendln();
        }
        sb.pop();
        
        sb.appendln("}");

        System.out.println(sb.toString());
    }

    private void appendAnnotations(StringBuilderPlus sb, Annotation[] annotations) {
        for (Annotation annotation : Arrays.asList(annotations)) {

            // getIndent()
            sb.appendln(
                "%s", 
                annotation.toString()
                    .replace("(", String.format("(\n%s   ", sb.getIndent()))
                    .replace(")", String.format("\n%s)", sb.getIndent()))
                    .replace(", ", String.format(",\n%s   ", sb.getIndent()))
            );
        }
    }

    private void appendExecutables(StringBuilderPlus sb, Executable[] executables) {
        for (Executable executable : Arrays.asList(executables)) {
            appendAnnotations(sb, executable.getDeclaredAnnotations());

            sb.appendln(executable.toGenericString() + " {}");
        }
    }
}
