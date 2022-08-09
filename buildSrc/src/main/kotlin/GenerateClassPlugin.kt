import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.objectweb.asm.*
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.util.TraceClassVisitor
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter


/**
 *
 * @author gleb.maliborsky
 */
abstract class GenerateClassPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val cw = ClassWriter(ClassWriter.COMPUTE_MAXS)
        //val adapter = AddFieldAdapter(cw, ACC_PUBLIC, "ura", "I")

        /* cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "GeneratedClass", null, "java/lang/Object", null)

         val mv: MethodVisitor? =
             cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
         mv?.visitCode()
         mv?.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
         mv?.visitLdcInsn("Hello world!")
         mv?.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false)
         mv?.visitInsn(Opcodes.RETURN)
         mv?.visitMaxs(0, 0)
         mv?.visitEnd()

         cw.visitEnd()*/

        var recordComponentVisitor: RecordComponentVisitor
        var annotationVisitor0: AnnotationVisitor

        cw.visit(
            V11,
            ACC_SUPER,
            "com/devloper/squad/punkbeer/Test123",
            null,
            "java/lang/Object",
            arrayOf("kotlin/jvm/functions/Function0")
        )

        var fieldVisitor: FieldVisitor = cw.visitField(ACC_PRIVATE, "a", "Lkotlin/jvm/functions/Function0;", null, null)
        fieldVisitor.visitEnd()
        var methodVisitor: MethodVisitor = cw.visitMethod(ACC_PUBLIC, "<init>", "(Lkotlin/jvm/functions/Function0;)V", null, null)
        methodVisitor.visitCode()
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitVarInsn(ALOAD, 1)
        methodVisitor.visitFieldInsn(PUTFIELD, "com/devloper/squad/punkbeer/Test123", "a", "Lkotlin/jvm/functions/Function0;")
        methodVisitor.visitInsn(RETURN)
        methodVisitor.visitMaxs(2, 2)
        methodVisitor.visitEnd()
        methodVisitor = cw.visitMethod(ACC_PUBLIC, "invoke", "()Ljava/lang/Object;", null, null)
        methodVisitor.visitCode()
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/devloper/squad/punkbeer/Test123", "a", "Lkotlin/jvm/functions/Function0;")
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "kotlin/jvm/functions/Function0", "invoke", "()Ljava/lang/Object;", true)
        methodVisitor.visitInsn(ARETURN)
        methodVisitor.visitMaxs(1, 1)
        methodVisitor.visitEnd()
        cw.visitEnd()

        // Write the bytes as a class file

        // Write the bytes as a class file
        val bytes = cw.toByteArray()
        try {
            FileOutputStream("Test123.class").use { stream -> stream.write(bytes) }
        } catch (e: Exception) {
            e.printStackTrace()
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
            return if (parameters.get().writeToStdout.get()) {
                TraceClassVisitor(nextClassVisitor, PrintWriter(System.out))
            } else {
                TraceClassVisitor(nextClassVisitor, PrintWriter(File("trace_out")))
            }
        }

        override fun isInstrumentable(classData: ClassData): Boolean {
            return classData.className.startsWith("com.devloper.squad.punkbeer.TestActivity")
        }
    }
}