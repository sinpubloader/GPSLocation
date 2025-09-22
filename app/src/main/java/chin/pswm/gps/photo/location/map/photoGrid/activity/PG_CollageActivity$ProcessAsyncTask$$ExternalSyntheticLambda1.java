   package chin.pswm.gps.photo.location.map.photoGrid.activity;

import chin.pswm.gps.photo.location.map.photoGrid.activity.PG_CollageActivity;

public final  class PG_CollageActivity$ProcessAsyncTask$$ExternalSyntheticLambda1 implements Runnable {
    public final  PG_CollageActivity.ProcessAsyncTask f$0;
    public final  String f$1;

    public  PG_CollageActivity$ProcessAsyncTask$$ExternalSyntheticLambda1(PG_CollageActivity.ProcessAsyncTask processAsyncTask, String str) {
        this.f$0 = processAsyncTask;
        this.f$1 = str;
    }

    public final void run() {
        f$0.onPostExecute$lambda$1(this.f$0, this.f$1);
    }
}