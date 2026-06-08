package Darah;


import java.time.LocalDateTime;

import App.App;
import User.FasilitasDarah;

public abstract class TesDarah {

    protected FasilitasDarah fasilitasDarah;
    protected LocalDateTime waktuTes;
    protected SampelDarah sampelDarahPendonor;

    public TesDarah(FasilitasDarah fasilitasDarah, SampelDarah sampelDarahPendonor) {
        this.fasilitasDarah = fasilitasDarah;
        this.waktuTes = LocalDateTime.now();
        this.sampelDarahPendonor = sampelDarahPendonor;
        fasilitasDarah.getListTesDarah().add(this);
    }

    abstract public void cetakHasil();

    abstract public void formInput(App app);

}
