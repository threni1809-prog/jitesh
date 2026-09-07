package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Crafting Guide", appName)
  }

  @Test
  fun `verify recipe database has recipes`() {
    val recipes = com.example.model.RecipeDatabase.RECIPES
    assert(recipes.isNotEmpty())
    val diamondSword = com.example.model.RecipeDatabase.findById("diamond_sword")
    assert(diamondSword != null)
  }

  @Test
  fun `verify texture pack sprites load correctly`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    com.example.ui.sprite.MinecraftSprites.init(context)
    val sprite = com.example.ui.sprite.MinecraftSprites.getSprite("diamond_sword")
    assert(sprite.width > 0)
    assert(sprite.height > 0)
    val craftingTable = com.example.ui.sprite.MinecraftSprites.getSprite("crafting_table")
    assert(craftingTable.width > 0)
    assert(craftingTable.height > 0)
  }
}
