import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.ASM9
import org.slf4j.LoggerFactory


/**
 *
 * @author gleb.maliborsky
 */
class AddFieldAdapter(
    cv: ClassVisitor?,
    fAcc: Int,
    fName: String,
    fDesc: String
) : ClassVisitor(ASM9, cv) {
    private val fAcc: Int
    private val fName: String
    private val fDesc: String
    private var isFieldPresent = false
    private var nameA: String? = null

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        nameA = name
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitField(
        access: Int, name: String, desc: String?,
        signature: String?, value: Any?
    ): FieldVisitor {
        if (name == fName) {
            isFieldPresent = true
        }
        return cv.visitField(access, name, desc, signature, value)
    }

    var aa = false
    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor? {
        val logger1 = LoggerFactory.getLogger("DynatraceTransformer123")
        logger1.debug("Jopa Kona {}   {}", name, nameA)
        val a = super.visitMethod(access, name, descriptor, signature, exceptions)
        System.out.println("Jopa Kona" + name)

        return if (a != null && name?.endsWith("toggleableImpl-3WzHGRc") == true) {
            ToggleableAdapter(a)
        } else if (a != null && name?.endsWith("clickable-O2vRcR0") == true && nameA?.endsWith("semantics") == true) {
            RemoveNopAdapter(a)
        } else {
            a
        }
//handlePressInteraction
        /* return if(name == "clickable-XHw0xAI") {
             val logger1 = LoggerFactory.getLogger("DynatraceTransformer1")
             logger1.debug("Jopa Kona{}   {}   {}", name)
             RemoveNopAdapter(super.visitMethod(access, name, descriptor, signature, exceptions))
         } else {
             val logger1 = LoggerFactory.getLogger("DynatraceTransformer1")
             logger1.debug("Jopa Kona{}   {}   {}", name)
             super.visitMethod(access, name, descriptor, signature, exceptions)
         }*/
        /*if(access != ACC_PUBLIC) {
        } else {
            return null;
        }*/
    }

    /*override fun visitEnd() {
       *//* if (!isFieldPresent) {
            val fv: FieldVisitor = cv.visitField(fAcc, fName, fDesc, null, null)
            if (fv != null) {
                fv.visitEnd()
            }

            val mv: MethodVisitor =
                cv.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
            mv.visitCode()
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
            mv.visitLdcInsn("Hello world!")
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false)
            mv.visitInsn(Opcodes.RETURN)
            mv.visitMaxs(0, 0)
            mv.visitEnd()
        }*//*
        cv.visitEnd()
    }*/

    init {
        this.fAcc = fAcc
        this.fName = fName
        this.fDesc = fDesc
    }
}