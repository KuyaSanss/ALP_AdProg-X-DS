package Darah;


import java.time.LocalDateTime;
import User.FasilitasDarah;

public abstract class TesDarah {

    protected FasilitasDarah fasilitasDarah;
    protected LocalDateTime waktuTes;
    protected SampelDarah sampelDarahPendonor;

    public TesDarah(FasilitasDarah fasilitasDarah, SampelDarah sampelDarahPendonor) {
        this.fasilitasDarah = fasilitasDarah;
        this.waktuTes = LocalDateTime.now();
        this.sampelDarahPendonor = sampelDarahPendonor;
        sampelDarahPendonor.getListTesDarah().add(this);
}


}
