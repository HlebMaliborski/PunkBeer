import com.android.build.api.instrumentation.*
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes.ACC_PUBLIC

/**
 *
 * @author gleb.maliborsky
 */
abstract class ExamplePlugin : Plugin<Project> {

    override fun apply(project: Project) {

        val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)

        androidComponents.onVariants { variant ->
            variant.transformClassesWith(
                ExampleClassVisitorFactory::class.java,
                InstrumentationScope.ALL
            ) {
                it.writeToStdout.set(true)
            }
            variant.setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)
        }
    }

    interface ExampleParams : InstrumentationParameters {
        @get:Input
        val writeToStdout: Property<Boolean>
    }

    abstract class ExampleClassVisitorFactory :
        AsmClassVisitorFactory<ExampleParams> {

        override fun createClassVisitor(
            classContext: ClassContext,
            nextClassVisitor: ClassVisitor
        ): ClassVisitor {
            /* val logger1 = LoggerFactory.getLogger("DynatraceTransformer")
             logger1.debug("Jopa {}", classContext.currentClassData.className)*/
            return AddFieldAdapter(nextClassVisitor, ACC_PUBLIC, "ura", "I")
            /*return if (parameters.get().writeToStdout.get()) {
                TraceClassVisitor(nextClassVisitor, PrintWriter(System.out))
            } else {
                TraceClassVisitor(nextClassVisitor, PrintWriter(File("trace_out")))
            }*/
        }

        override fun isInstrumentable(classData: ClassData): Boolean {
            //return true
            //return classData.className.equals("androidx.compose.foundation.ClickableKt\u0024clickable")
            //return true
            //return classData.className.contains("androidx.compose.ui.semantics.SemanticsPropertiesKt")
            //return classData.className.equals("androidx.compose.foundation.ClickableKt")
            return classData.className == "androidx.compose.foundation.selection.ToggleableKt"
        }
    }
}