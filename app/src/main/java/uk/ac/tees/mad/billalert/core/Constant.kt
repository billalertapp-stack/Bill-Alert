package uk.ac.tees.mad.billalert.core

object Constants {
    // Supabase table names
    const val TABLE_BILLS = "bills"
    const val TABLE_USERS = "users"

    // Notification
    const val NOTIFICATION_CHANNEL_ID = "bill_reminders"
    const val NOTIFICATION_CHANNEL_NAME = "Bill Reminders"

    // DataStore
    const val DATASTORE_NAME = "bill_alert_prefs"
    const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"

    // WorkManager tags
    const val WORK_TAG_BILL_REMINDER = "bill_reminder_"
}