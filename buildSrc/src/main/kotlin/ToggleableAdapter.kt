import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*

/**
 *
 * @author gleb.maliborsky
 */
class ToggleableAdapter(methodVisitor: MethodVisitor) : MethodVisitor(ASM9, methodVisitor) {
    override fun visitCode() {
        mv?.visitTypeInsn(NEW, "com/devloper/squad/feature_beer/presentation/ToggleableComposeCallback");
        mv?.visitInsn(DUP);
        mv?.visitVarInsn(ALOAD, 6);
        mv?.visitVarInsn(ALOAD, 3);
        mv?.visitVarInsn(ALOAD, 1);
        mv?.visitMethodInsn(
            INVOKESPECIAL,
            "com/devloper/squad/feature_beer/presentation/ToggleableComposeCallback",
            "<init>",
            "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/semantics/Role;Landroidx/compose/ui/state/ToggleableState;)V",
            false
        );
        mv?.visitVarInsn(ASTORE, 6);
        super.visitCode()
    }
}