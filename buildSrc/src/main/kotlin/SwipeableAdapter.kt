import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*

/**
 *
 * @author gleb.maliborsky
 */
class SwipeableAdapter(methodVisitor: MethodVisitor) : MethodVisitor(ASM9, methodVisitor) {
    override fun visitCode() {
        mv?.visitVarInsn(ALOAD, 1);
        mv?.visitMethodInsn(
            INVOKEVIRTUAL,
            "androidx/compose/material/SwipeableState",
            "getCurrentValue",
            "()Ljava/lang/Object;",
            false
        );
        mv?.visitVarInsn(ALOAD, 1);
        mv?.visitMethodInsn(
            INVOKEVIRTUAL,
            "androidx/compose/material/SwipeableState",
            "getTargetValue",
            "()Ljava/lang/Object;",
            false
        );
        mv?.visitVarInsn(ALOAD, 1);
        mv?.visitMethodInsn(
            INVOKEVIRTUAL,
            "androidx/compose/material/SwipeableState",
            "getProgress",
            "()Landroidx/compose/material/SwipeProgress;",
            false
        );
        mv?.visitMethodInsn(INVOKEVIRTUAL, "androidx/compose/material/SwipeProgress", "getFraction", "()F", false);
        mv?.visitMethodInsn(
            INVOKESTATIC,
            "com/devloper/squad/feature_beer/presentation/SwipeableParser",
            "enterAction",
            "(Ljava/lang/Object;Ljava/lang/Object;F)V",
            false
        );
        super.visitCode()
    }
}