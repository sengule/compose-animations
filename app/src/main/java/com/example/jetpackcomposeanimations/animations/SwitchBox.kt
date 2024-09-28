package com.example.jetpackcomposeanimations.animations

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Preview(showBackground =  true)
@Composable
fun SwitchBox(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        SwitchBoxContent()
    }
}

// Space between boxes. It is important for the animation to work, because size between
// boxes is measured as dp and animated with that way. Space between boxes is represented with
// Spacer composable but in real applications, it needs to be measured.
const val SPACE_BETWEEN = 20

const val BOX_SIZE = 120

@Composable
fun SwitchBoxContent(
    modifier: Modifier = Modifier
) {
    var isSwitched by remember { mutableStateOf(false) }

    val pxToMove = with(LocalDensity.current) {
        (BOX_SIZE + SPACE_BETWEEN).dp.toPx().roundToInt()
    }

    val position by animateIntOffsetAsState(
        animationSpec = spring(
            stiffness = Spring.StiffnessLow
        ),
        targetValue = if (isSwitched) IntOffset(pxToMove,0) else IntOffset.Zero,
        label = ""
    )

    val itemModifier = Modifier
        .size(BOX_SIZE.dp)

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = modifier.fillMaxWidth().padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            BoxItem(
                modifier = itemModifier.offset{ position },
                color = Color.Blue
            )

            // Space between boxes
            Spacer(modifier.width(SPACE_BETWEEN.dp))

            BoxItem(
                modifier = itemModifier.offset{ -position },
                color = Color.Red
            )
        }

        Button(
            onClick = { isSwitched = !isSwitched},
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Switch")
        }
    }

}

@Composable
fun BoxItem(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    color: Color = Color.Gray,
){
    Box(
        modifier = modifier.background(color, shape),
        contentAlignment = Alignment.Center,
    ){

    }
}
