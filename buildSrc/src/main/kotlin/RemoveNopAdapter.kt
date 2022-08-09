import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.*

/**
 *
 * @author gleb.maliborsky
 */
class RemoveNopAdapter(private val methodVisitor: MethodVisitor) : MethodVisitor(ASM9, methodVisitor) {
    override fun visitCode() {

        mv?.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
        mv?.visitLdcInsn("Hello world!")
        mv?.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false)


       /* mv?.visitLdcInsn("empty");
        mv?.visitVarInsn(ASTORE, 10);
        mv?.visitTypeInsn(NEW, "com/devloper/squad/punkbeer/Ura123");
        mv?.visitInsn(DUP);
        mv?.visitVarInsn(ALOAD, 4);
        mv?.visitVarInsn(ALOAD, 3);
        mv?.visitMethodInsn(INVOKEVIRTUAL, "androidx/compose/ui/semantics/Role", "toString", "()Ljava/lang/String;", false);
        mv?.visitMethodInsn(INVOKESPECIAL, "com/devloper/squad/punkbeer/Ura123", "<init>", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;)V", false);
        mv?.visitVarInsn(ASTORE, 4);*/


        mv?.visitLdcInsn("empty");
        mv?.visitVarInsn(ASTORE, 10);
        mv?.visitVarInsn(ALOAD, 5);
        val label3 = Label();
        mv?.visitJumpInsn(IFNULL, label3);
        mv?.visitVarInsn(ALOAD, 5);
        mv?.visitMethodInsn(INVOKEVIRTUAL, "androidx/compose/ui/semantics/Role", "toString", "()Ljava/lang/String;", false);
        mv?.visitVarInsn(ASTORE, 10);
        mv?.visitLabel(label3);
        mv?.visitTypeInsn(NEW, "com/devloper/squad/punkbeer/Ura123");
        mv?.visitInsn(DUP);
        mv?.visitVarInsn(ALOAD, 6);
        mv?.visitVarInsn(ALOAD, 10);
        mv?.visitMethodInsn(INVOKESPECIAL, "com/devloper/squad/punkbeer/Ura123", "<init>", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;)V", false);
        mv?.visitVarInsn(ASTORE, 6);
        super.visitCode()
    }
/*    override fun visitInsn(opcode: Int) {
        mv.visitInsn(opcode)
        mv.visitLdcInsn("ura1");
        mv.visitLdcInsn("ura1");
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(POP);
        mv.visitMaxs(2, 0)
        //methodVisitor.visitInsn(RETURN);
        *//*if (opcode != ICONST_1) {
            methodVisitor.visitInsn(opcode)
        }*//*
    }*/

    override fun visitEnd() {
        /*mv.visitLdcInsn("ura1");
        mv.visitLdcInsn("ura1");
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);*/
        /*mv.visitInsn(POP);
        mv.visitMaxs(2, 0);*/
        super.visitEnd()
    }
}