import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SeachFiles {
    public static void main(String[] args) {
        ArrayList<File> fileList = new ArrayList<>(); //создаем пустой лист, хранящий тип данных - файл
        toSearchFiles(new File("D:\\"), fileList);//вызывае метод, передаем в него файл и пустой лист
        for (File file: fileList){//выводим заполненный лист
            System.out.println(file.getAbsolutePath());
        }

    }
    public static void toSearchFiles(File root_file, List<File> fileList){
        if (root_file.isDirectory()){ //проверяем, является ли файл директорией

            File[] directoryFiles = root_file.listFiles();// создаем массив файлов, содержащихся в корневой папке
            if (directoryFiles!=null){ //проверяем, пустая директория или нет
                for (File file: directoryFiles){//проходим по всем файлам, содержащимся в директории
                    if (file.isDirectory()){//если рассматриваемый файл - директория, то снова вызываем метод(рекурсия)
                        toSearchFiles(file, fileList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".jpg"))//если не директория, то проверяем на необходимое расширение
                            fileList.add(file);//добавлем подходящие файлы в лист
                    }
                }
            }
        }
    }
}
