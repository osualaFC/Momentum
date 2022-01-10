package com.fredosuala.momentum.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

@Composable
fun AppTopBar(
    navController: NavController,
    title : String,
    @DrawableRes endIcon : Int,
    endContentDes : String,
    endAction : () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
           val  (leftIcon, header, rightIcon) = createRefs()
            BackIcon(
                navController = navController,
                modifier = Modifier.constrainAs(leftIcon){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
            )
            Text(
                text = title,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.constrainAs(header){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(leftIcon.end)
                    end.linkTo(rightIcon.start)
                }
            )
            RightIcon(
                icon = endIcon,
                contentDes = endContentDes,
                modifier = Modifier.constrainAs(rightIcon){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
            ) {}
        }
    }
}