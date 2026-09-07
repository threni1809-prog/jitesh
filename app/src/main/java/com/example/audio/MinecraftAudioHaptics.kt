package com.example.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import com.example.R
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.random.Random

/**
 * Audio and Haptic engine for Minecraft Crafting.
 *
 * Implements:
 * - Classic Minecraft 'click' sound when placing an item in a slot (with authentic pitch randomization).
 * - Classic Minecraft 'crafting' sound when a recipe is completed (authentic pop + harmonic levelup chime).
 * - Item pop sound for picking up or clearing items.
 * - Micro wooden tick sound when hovering over slots during drag.
 * - Tactile haptic feedback for dragging, slot hovering, item placement, and recipe completion.
 */
object MinecraftAudioHaptics {
    private const val TAG = "MinecraftAudioHaptics"

    private var soundPool: SoundPool? = null
    private var clickSoundId: Int = 0
    private var craftSoundId: Int = 0
    private var popSoundId: Int = 0
    private var tickSoundId: Int = 0

    private val isLoaded = AtomicBoolean(false)
    private var vibrator: Vibrator? = null

    var isSoundEnabled: Boolean = true
    var isHapticsEnabled: Boolean = true

    fun initialize(context: Context) {
        if (soundPool != null) return
        val appContext = context.applicationContext

        // Initialize Vibrator
        try {
            vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vm = appContext.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vm?.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                appContext.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            }
        } catch (e: Exception) {
            Log.w(TAG, "Failed to initialize vibrator: ${e.message}")
        }

        // Initialize SoundPool with GAME usage for zero audio latency
        try {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            val sp = SoundPool.Builder()
                .setMaxStreams(8)
                .setAudioAttributes(audioAttributes)
                .build()

            sp.setOnLoadCompleteListener { _, _, status ->
                if (status == 0) {
                    isLoaded.set(true)
                }
            }

            clickSoundId = sp.load(appContext, R.raw.mc_click, 1)
            craftSoundId = sp.load(appContext, R.raw.mc_craft, 1)
            popSoundId = sp.load(appContext, R.raw.mc_pop, 1)
            tickSoundId = sp.load(appContext, R.raw.mc_drag_tick, 1)

            soundPool = sp
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize SoundPool: ${e.message}")
        }
    }

    /**
     * Classic Minecraft Click sound when an item is placed in a 3x3 slot.
     * Slightly randomizes pitch (0.95f - 1.05f) replicating vanilla Minecraft audio physics.
     */
    fun playClickSound(volume: Float = 1.0f) {
        if (!isSoundEnabled) return
        val sp = soundPool ?: return
        try {
            val pitch = 0.95f + Random.nextFloat() * 0.10f
            sp.play(clickSoundId, volume, volume, 1, 0, pitch)
        } catch (e: Exception) {
            Log.w(TAG, "Error playing click sound: ${e.message}")
        }
    }

    /**
     * Classic Minecraft Crafting sound when a recipe is completed.
     * Plays the authentic Minecraft pop and resonant harmonic levelup chime.
     */
    fun playCraftingSound(volume: Float = 1.0f) {
        if (!isSoundEnabled) return
        val sp = soundPool ?: return
        try {
            sp.play(craftSoundId, volume, volume, 2, 0, 1.0f)
        } catch (e: Exception) {
            Log.w(TAG, "Error playing crafting sound: ${e.message}")
        }
    }

    /**
     * Item Pop sound (for picking up an item to drag, or clearing a slot).
     */
    fun playPopSound(volume: Float = 0.85f) {
        if (!isSoundEnabled) return
        val sp = soundPool ?: return
        try {
            val pitch = 0.93f + Random.nextFloat() * 0.14f
            sp.play(popSoundId, volume, volume, 1, 0, pitch)
        } catch (e: Exception) {
            Log.w(TAG, "Error playing pop sound: ${e.message}")
        }
    }

    /**
     * Micro wooden tick sound for hovering over matrix slots during drag.
     */
    fun playDragTickSound(volume: Float = 0.45f) {
        if (!isSoundEnabled) return
        val sp = soundPool ?: return
        try {
            sp.play(tickSoundId, volume, volume, 0, 0, 1.0f)
        } catch (e: Exception) {
            Log.w(TAG, "Error playing drag tick sound: ${e.message}")
        }
    }

    // ==========================================
    // HAPTIC FEEDBACK (Tactile Physical Response)
    // ==========================================

    /**
     * Tactile click when an item is placed into a slot.
     */
    fun hapticItemPlaced() {
        if (!isHapticsEnabled) return
        try {
            val v = vibrator ?: return
            if (!v.hasVibrator()) return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(18, 180))
            } else {
                @Suppress("DEPRECATION")
                v.vibrate(18)
            }
        } catch (_: Exception) {}
    }

    /**
     * Tactile tick when dragging an item and hovering across a slot boundary.
     */
    fun hapticSlotHover() {
        if (!isHapticsEnabled) return
        try {
            val v = vibrator ?: return
            if (!v.hasVibrator()) return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK))
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(8, 90))
            } else {
                @Suppress("DEPRECATION")
                v.vibrate(8)
            }
        } catch (_: Exception) {}
    }

    /**
     * Rich celebratory haptic burst when a recipe is completed / synthesized.
     */
    fun hapticRecipeCrafted() {
        if (!isHapticsEnabled) return
        try {
            val v = vibrator ?: return
            if (!v.hasVibrator()) return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK))
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val timings = longArrayOf(0, 25, 40, 50)
                val amplitudes = intArrayOf(0, 180, 0, 255)
                v.vibrate(VibrationEffect.createWaveform(timings, amplitudes, -1))
            } else {
                @Suppress("DEPRECATION")
                v.vibrate(50)
            }
        } catch (_: Exception) {}
    }

    /**
     * Tactile item pickup when initiating a drag gesture.
     */
    fun hapticItemPickup() {
        if (!isHapticsEnabled) return
        try {
            val v = vibrator ?: return
            if (!v.hasVibrator()) return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK))
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(12, 140))
            } else {
                @Suppress("DEPRECATION")
                v.vibrate(12)
            }
        } catch (_: Exception) {}
    }
}
