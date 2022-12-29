package Seriaziable;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        GameProgress gameProgress1 = new GameProgress(100, 200, 5, 30.0d);
        GameProgress gameProgress2 = new GameProgress(100, 50, 2, 10.0d);
        GameProgress gameProgress3 = new GameProgress(100, 10, 1, 35.0d);

        saveGame("C://Users//Games//savegames/save1.txt", gameProgress1);
        saveGame("C://Users//Games//savegames/save2.txt", gameProgress2);
        saveGame("C://Users//Games//savegames/save3.txt", gameProgress3);
        zipFiles("C://Users//Games//savegames/MyZip.zip", new String[]{"C://Users//Games//savegames/save1.txt",
                "C://Users//Games//savegames/save2.txt",
                "C://Users//Games//savegames/save3.txt"});

        deleteSaves();

    }

    public static void saveGame(String url, GameProgress gameProgress) {
        File fileSaveDat = new File(url);
        try {
            fileSaveDat.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSaveDat));
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(String urlZip, String[] files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(urlZip))) {

            ZipEntry zip = new ZipEntry("allSaves");

            zos.putNextEntry(zip);
            for (String fileToSave : files) {
                FileInputStream fis = new FileInputStream(fileToSave);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zos.write(buffer);
            }
            zos.closeEntry();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSaves(){
        File[] files = new File[]{new File("C://Users//Games//savegames/save1.txt"),
                new File("C://Users//Games//savegames/save2.txt"),
                new File("C://Users//Games//savegames/save3.txt")};

        for(int i=0;  i<3; i++){
            if(files[i].exists()){
                files[i].delete();
            }
        }
    }

}
