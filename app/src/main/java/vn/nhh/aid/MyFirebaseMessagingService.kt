package vn.nhh.aid
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("TOKEN: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.body?.let {
            this.showNotification(
                messageBody = it,
                messageTitle = "App sơ cấp cứu",
            )
        }
    }
}
fun Context.showNotification(
    messageBody: String,
    messageTitle: String = "",
    requestCode: Int = 0,
    intent: Intent = Intent(this, MainActivity::class.java),
    channelId: String = "fcm_default_channel_id",
    channelName: String = "fcm_default_channel_name",
    soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
): Int {
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    val pendingIntent = PendingIntent.getActivity(
        this,
        requestCode,
        intent,
        PendingIntent.FLAG_IMMUTABLE,
    )
    val notificationBuilder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(messageTitle)
        .setContentText(messageBody)
        .setAutoCancel(true)
        .setSound(soundUri)
        .setContentIntent(pendingIntent)

    val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    notificationManager.createNotificationChannel(NotificationChannel(
        channelId,
        channelName,
        NotificationManager.IMPORTANCE_DEFAULT,
    ))

    val notificationId = "${System.currentTimeMillis()}".takeLast(9).toInt() //726228478
    notificationManager.notify(notificationId, notificationBuilder.build())
    return notificationId
}
//class NotificationFunction {
//    companion object {
//        fun presentNotification(
//            messageBody: String,
//            messageTitle: String = "",
//            context: Context,
//            requestCode: Int = 0,
//            intent: Intent = Intent(context, MainActivity::class.java),
//            channelId: String = "fcm_default_channel_id",
//            channelName: String = "fcm_default_channel_name",
//            soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
//        ): Int {
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//            val pendingIntent = PendingIntent.getActivity(
//                context,
//                requestCode,
//                intent,
//                PendingIntent.FLAG_IMMUTABLE,
//            )
//
//            val notificationBuilder = NotificationCompat.Builder(context, channelId)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(messageTitle)
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(soundUri)
//                .setContentIntent(pendingIntent)
//
//            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//            notificationManager.createNotificationChannel(NotificationChannel(
//                channelId,
//                channelName,
//                NotificationManager.IMPORTANCE_DEFAULT,
//            ))
//
//            val notificationId = "${System.currentTimeMillis()}".takeLast(9).toInt() //726228478
//            notificationManager.notify(notificationId, notificationBuilder.build())
//            return notificationId
//        }
//    }
//
//}