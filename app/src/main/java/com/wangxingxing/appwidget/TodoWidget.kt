package com.wangxingxing.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.AppWidgetTarget

/**
 * Implementation of App Widget functionality.
 */
class TodoWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.todo_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    Log.i(TAG, "updateAppWidget: start")
    val appWidgetTarget = AppWidgetTarget(context, 200, 200, R.id.iv_big, views, R.id.iv_big)
    Glide.with(context.applicationContext)
        .asBitmap()
        .load("https://cdn.sbnh.cn/ic_cool.png")
        .into(appWidgetTarget)
    Log.i(TAG, "updateAppWidget: end")

//    views.setImageViewResource(R.id.iv_big, R.mipmap.ic_hamburger)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)

    views.setOnClickPendingIntent(R.id.iv_big, openAppPendingIntent(context, R.id.iv_big))
}

private fun openAppPendingIntent(context: Context,@IdRes id:Int):PendingIntent{
    /*打开APP intent*/
    val activityIntent = Intent(context, MainActivity::class.java).apply {
        setData(Uri.parse("harvic:$id"))
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }
    val appOpenIntent = PendingIntent.getActivity(
        context,
        1,
        activityIntent,
        PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    return appOpenIntent
}