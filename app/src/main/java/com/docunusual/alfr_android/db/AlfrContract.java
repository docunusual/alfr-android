package com.docunusual.alfr_android.db;

import android.net.Uri;
import android.provider.BaseColumns;

public final class AlfrContract {

    public static final String AUTHORITY = "com.docunusual.alfr.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final class Events implements BaseColumns {

        private Events() {
        }

        /**
         * The content:// style URI for this table.  Requests to this URI can be
         * performed on the UI thread because they are always unblocking.
         */
        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(AUTHORITY_URI, "events");

        /**
         * The MIME-type of {@link #CONTENT_URI} providing a directory of
         * contact directories.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/alfr_events";

        /**
         * The MIME type of a {@link #CONTENT_URI} item.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/alfr_event";

        public static final String BY = "by";

        public static final String INLINE = "inline";

    }
}
