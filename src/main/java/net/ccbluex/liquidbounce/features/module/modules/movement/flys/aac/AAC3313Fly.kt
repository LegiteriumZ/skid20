package net.ccbluex.liquidbounce.features.module.modules.movement.flys.aac

import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.modules.movement.flys.FlyMode
import net.ccbluex.liquidbounce.value.FloatValue
import org.lwjgl.input.Keyboard

class AAC3313Fly : FlyMode("AAC3.3.13") {
    private val motionValue = FloatValue("${valuePrefix}Motion", 10f, 0.1f, 10f)

    private var wasDead = false

    override fun onDisable() {
        wasDead=false
    }

    override fun onUpdate(event: UpdateEvent) {
        if(mc.thePlayer.isDead)
            wasDead = true

        if(wasDead || mc.thePlayer.onGround) {
            wasDead = false

            mc.thePlayer.motionY = motionValue.get().toDouble()
            mc.thePlayer.onGround = false
        }

        mc.timer.timerSpeed = 1F

        if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            mc.timer.timerSpeed = 0.2F
            mc.rightClickDelayTimer = 0
        }
    }
}