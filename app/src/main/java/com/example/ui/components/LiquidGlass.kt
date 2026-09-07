package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MinecraftDiamond
import com.example.ui.theme.MinecraftGreen
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

/**
 * Apple Elastic Squash & Stretch modifier.
 * Provides the signature physical rubber-band compression on touch down,
 * followed by an elastic spring overshoot and recoil stretch when released.
 */
fun Modifier.elasticGlassPress(
    pressScaleX: Float = 0.94f,
    pressScaleY: Float = 0.94f,
    onClick: (() -> Unit)? = null
): Modifier = composed {
    val scaleX = remember { Animatable(1f) }
    val scaleY = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()

    this
        .graphicsLayer {
            this.scaleX = scaleX.value
            this.scaleY = scaleY.value
        }
        .pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    awaitFirstDown(requireUnconsumed = false)
                    // Touch down: smooth compression
                    scope.launch {
                        scaleX.animateTo(pressScaleX, tween(110, easing = FastOutSlowInEasing))
                    }
                    scope.launch {
                        scaleY.animateTo(pressScaleY, tween(110, easing = FastOutSlowInEasing))
                    }

                    val up = waitForUpOrCancellation()
                    if (up != null) {
                        // Released: rubber-band elastic spring stretch bounce
                        scope.launch {
                            scaleX.animateTo(
                                targetValue = 1f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessMediumLow
                                )
                            )
                        }
                        scope.launch {
                            scaleY.animateTo(
                                targetValue = 1f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessMediumLow
                                )
                            )
                        }
                        onClick?.invoke()
                    } else {
                        // Cancelled
                        scope.launch { scaleX.animateTo(1f, tween(150)) }
                        scope.launch { scaleY.animateTo(1f, tween(150)) }
                    }
                }
            }
        }
}

/**
 * Apple visionOS liquid glass canvas with dynamically drifting caustic light orbs.
 */
@Composable
fun LiquidGlassBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "caustic_drift")

    val orbitAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(24000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "orbit"
    )

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF090D14)) // Deep Obsidian Liquid Matrix
    ) {
        // Ambient chromatic refraction orbs underneath the frosted glass surface
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val rad = Math.toRadians(orbitAngle.toDouble())
            val cosVal = cos(rad).toFloat()
            val sinVal = sin(rad).toFloat()

            // 1. Cyan/Diamond Caustic Bloom (top right)
            val cyanX = w * (0.80f + 0.10f * cosVal)
            val cyanY = h * (0.15f + 0.08f * sinVal)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x404AEDD9),
                        Color(0x184AEDD9),
                        Color.Transparent
                    ),
                    center = Offset(cyanX, cyanY),
                    radius = w * 0.75f * pulseScale
                ),
                radius = w * 0.75f * pulseScale,
                center = Offset(cyanX, cyanY)
            )

            // 2. Emerald Caustic Bloom (center left)
            val emeraldX = w * (0.18f + 0.08f * sinVal)
            val emeraldY = h * (0.48f + 0.12f * cosVal)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x3843A047),
                        Color(0x1243A047),
                        Color.Transparent
                    ),
                    center = Offset(emeraldX, emeraldY),
                    radius = w * 0.80f * (2f - pulseScale)
                ),
                radius = w * 0.80f * (2f - pulseScale),
                center = Offset(emeraldX, emeraldY)
            )

            // 3. Violet/Amethyst Caustic Bloom (bottom right)
            val violetX = w * (0.82f - 0.10f * sinVal)
            val violetY = h * (0.85f + 0.06f * cosVal)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x358A3FFC),
                        Color(0x108A3FFC),
                        Color.Transparent
                    ),
                    center = Offset(violetX, violetY),
                    radius = w * 0.70f
                ),
                radius = w * 0.70f,
                center = Offset(violetX, violetY)
            )

            // 4. Electric Blue Core Highlight (top center)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x280088FF),
                        Color.Transparent
                    ),
                    center = Offset(w * 0.50f, h * 0.05f),
                    radius = w * 0.55f
                ),
                radius = w * 0.55f,
                center = Offset(w * 0.50f, h * 0.05f)
            )
        }

        content()
    }
}

/**
 * Authentic Apple visionOS Liquid Glass Panel.
 * Features a high-gloss specular rim (white specular bevel on top-left fading to refraction cyan),
 * internal lens curvature, and a top specular reflection arc.
 */
@Composable
fun LiquidGlassPanel(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(20.dp),
    borderHighlightColor: Color = Color(0xF0FFFFFF),
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            // Frosted visionOS acrylic face
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0x2BFFFFFF), // 17% white top sheen
                        Color(0x13FFFFFF), // 7.5% mid
                        Color(0x08FFFFFF)  // 3% bottom
                    )
                )
            )
            // Signature Apple double-edge specular refraction rim
            .border(
                width = 1.25.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        borderHighlightColor,         // Top-left specular gleam
                        Color(0x30FFFFFF),            // Mid rim
                        Color(0x10FFFFFF),            // Lower rim
                        Color(0x354AEDD9)             // Subtle caustic cyan edge
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                ),
                shape = shape
            )
            // Lens meniscus top specular highlight line
            .drawWithContent {
                drawContent()
                // Delicate specular rim arc across top
                val highlightHeight = 1.5.dp.toPx()
                drawRoundRect(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0x99FFFFFF),
                            Color(0xDDFFFFFF),
                            Color(0x99FFFFFF),
                            Color.Transparent
                        )
                    ),
                    topLeft = Offset(12.dp.toPx(), 0.5.dp.toPx()),
                    size = Size(size.width - 24.dp.toPx(), highlightHeight),
                    cornerRadius = CornerRadius(highlightHeight / 2, highlightHeight / 2)
                )
            }
    ) {
        content()
    }
}

/**
 * Interactive Apple Liquid Glass Card with physical squash-and-stretch tactile response.
 */
@Composable
fun LiquidGlassCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(18.dp),
    testTag: String = "liquid_glass_card",
    content: @Composable BoxScope.() -> Unit
) {
    LiquidGlassPanel(
        modifier = modifier
            .testTag(testTag)
            .elasticGlassPress(
                pressScaleX = 0.95f,
                pressScaleY = 0.95f,
                onClick = onClick
            ),
        shape = shape,
        content = content
    )
}

/**
 * visionOS Pill-shaped Glass Badge with glowing lens rim.
 */
@Composable
fun LiquidGlassBadge(
    text: String,
    modifier: Modifier = Modifier,
    accentColor: Color = MinecraftDiamond,
    textColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(accentColor.copy(alpha = 0.18f))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    listOf(
                        Color.White.copy(alpha = 0.7f),
                        accentColor.copy(alpha = 0.45f)
                    )
                ),
                shape = CircleShape
            )
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.2.sp
        )
    }
}

/**
 * App Tabs
 */
enum class AppTab(val title: String, val icon: String) {
    RECIPES("Recipe Book", "📖"),
    SANDBOX("Crafting Sandbox", "🧪")
}

/**
 * Apple Vision Liquid Glass Tab Bar with **Fluid Liquid Mercury Stretches**.
 * When toggling between tabs, the sliding indicator elastically stretches along its travel axis
 * like liquid glass / mercury before snapping smoothly into place.
 */
@Composable
fun LiquidGlassTabBar(
    selectedTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabIndex = if (selectedTab == AppTab.RECIPES) 0 else 1

    // Elastic fluid position with spring physics
    val animatedIndex by animateFloatAsState(
        targetValue = tabIndex.toFloat(),
        animationSpec = spring(
            dampingRatio = 0.68f, // Medium bouncy
            stiffness = 380f      // Responsive spring
        ),
        label = "tab_pos"
    )

    // Dynamic liquid stretch calculation:
    // Distance from whole number represents travel speed -> translates to horizontal elongation
    val travelDist = kotlin.math.abs(animatedIndex - tabIndex.toFloat())
    val stretchFactor = 1f + travelDist * 0.45f // Stretches up to 145% during transit!

    LiquidGlassPanel(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(28.dp),
        borderHighlightColor = Color(0x80FFFFFF)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            // Liquid mercury stretching indicator pill
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .graphicsLayer {
                        val maxOffset = size.width
                        translationX = animatedIndex * maxOffset
                        // Apply liquid horizontal stretch while moving
                        scaleX = stretchFactor
                    }
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color(0x554AEDD9), // Cyan glowing liquid top
                                Color(0x281B4B43)  // Rich emerald base
                            )
                        )
                    )
                    .border(
                        width = 1.25.dp,
                        brush = Brush.linearGradient(
                            listOf(
                                Color(0xE6FFFFFF),
                                MinecraftDiamond.copy(alpha = 0.9f),
                                Color(0x334AEDD9)
                            )
                        ),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .drawWithContent {
                        drawContent()
                        // Apple specular meniscus sheen
                        drawRoundRect(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.White.copy(alpha = 0.75f),
                                    Color.Transparent
                                )
                            ),
                            topLeft = Offset(10.dp.toPx(), 1.dp.toPx()),
                            size = Size(size.width - 20.dp.toPx(), 1.5.dp.toPx()),
                            cornerRadius = CornerRadius(1f, 1f)
                        )
                    }
            )

            // Clickable Tab Labels
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AppTab.values().forEach { tab ->
                    val isSelected = tab == selectedTab

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(24.dp))
                            .elasticGlassPress(
                                pressScaleX = 0.92f,
                                pressScaleY = 0.92f,
                                onClick = { onTabSelected(tab) }
                            )
                            .testTag("tab_${tab.name.lowercase()}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = tab.icon,
                                fontSize = 15.sp
                            )
                            Box(modifier = Modifier.padding(start = 7.dp))
                            Text(
                                text = tab.title,
                                color = if (isSelected) Color.White else Color(0xFF9EAEC7),
                                fontSize = 13.5.sp,
                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.SemiBold,
                                letterSpacing = 0.2.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
