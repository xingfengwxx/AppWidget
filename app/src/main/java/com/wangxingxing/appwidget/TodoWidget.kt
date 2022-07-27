package com.wangxingxing.appwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
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

    Log.i("wxx", "updateAppWidget: start")
    val appWidgetTarget = AppWidgetTarget(context, 100, 100, R.id.iv_big, views, R.id.iv_big)
    Glide.with(context.applicationContext)
        .asBitmap()
        .load("https://cdn.sbnh.cn/ic_cool.png")
        .into(appWidgetTarget)
    Log.i("wxx", "updateAppWidget: end")

//    views.setImageViewResource(R.id.iv_big, R.mipmap.ic_hamburger)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}