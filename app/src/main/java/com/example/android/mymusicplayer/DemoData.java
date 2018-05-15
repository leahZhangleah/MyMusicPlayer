package com.example.android.mymusicplayer;

/**
 * Created by ceciliaHumlelu on 2018-04-01.
 */

public class DemoData {
    private AlbumDetail[] demoData = {
            new AlbumDetail(R.drawable.mal_cover,"Unknown",
            "Kapitel 1",new String[]{"Vad heter du","Lång Vokal!","Hej"},
            new int[]{R.raw.vad_heter_du,R.raw.long_vokal,R.raw.hej}),
            new AlbumDetail(R.drawable.mal_cover,"Unknown",
                    "Kapitel 2",new String[]{ "Alfabetet","Alfabetet 2","Hur stavas det?"},
                    new int[]{R.raw.alfabetet,R.raw.alfabetet_2,R.raw.hur_stavas_det}),
            new AlbumDetail(R.drawable.mal_cover,"Unknown",
                    "Kapitel 3",new String[]{"Länder och Värdsdelar","Var bor de A?","Var bor de C"},
                    new int[]{R.raw.laender_och_vaerdsdelar,R.raw.var_bor_de_a,R.raw.var_bor_de_c}),
            new AlbumDetail(R.drawable.mal_cover,"Unknown",
                    "Kapitel 4",new String[]{"Land och Språk","Var kommer du ifrån?","Var kommer du ifrån 2?"},
                    new int[]{R.raw.land_och_sprak,R.raw.var_kommer_du_ifran,R.raw.var_kommer_du_ifran_2})
    };

    public AlbumDetail[] getDemoData() {
        return demoData;
    }
}
