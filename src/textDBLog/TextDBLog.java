package textDBLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class TextDBLog {
	
	public static void main(String[] args) {
		new TextDBLog("db/db/db/bd/db/test.txt", new String[] {"はい。元気です\r\n","ではまた\r\n"});
	}
	
	public TextDBLog(String pathString, String[] text){
		Path path = FileSystems.getDefault().getPath(pathString);
		ArrayList<String> paths = new ArrayList<String>();
		paths.add(String.valueOf(path.toString()));
		for(int i=0;i < path.getNameCount();i++) {
			if(i != 0) {
				if(!isfile(paths.get(i) , true)) {
					paths.add(String.valueOf(path.getParent()));
					path = path.getParent();
				}else {
					break;
				}
			}else {
				if(!isfile(paths.get(i) , false)) {
					paths.add(String.valueOf(path.getParent()));
					path = path.getParent();
				}else {
					break;
				}
			}
		}
		if(paths.size() != 0) {
			for(int i=paths.size()-1;i >= 0;i--) {
				if(i != 0) {
					isfile(paths.get(i) , true);
				}else {
					isfile(paths.get(i) , false);
				}
			}
		}
		in(pathString , text);
	}
	
	public Boolean isfile(String path, boolean folder) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        } else {
        	return create(path,folder);
        }
	}
	
	public Boolean create(String path, boolean folder){
        File file = new File(path);
		try {
			if(folder) {
				if (file.mkdirs()){
				    System.out.println("ファイル作成成功1");
				}else{
				    System.out.println("ファイル作成失敗1");
				}
			}else {
				if (file.createNewFile()){
				    System.out.println("ファイル作成成功");
				}else{
				    System.out.println("ファイル作成失敗");
				}
			}
			return true;
		} catch (IOException e) {
			return false;
		}
		
	}
	public void in(String path, String[] text) {
		 try{
			 File file = new File(path);

		      if (checkBeforeWritefile(file)){
		        FileWriter filewriter = new FileWriter(file, true);

		        for(int i=0;i < text.length;i++) {
			        filewriter.write(text[i]);
		        }
		        filewriter.close();
		      }else{
		        System.out.println("ファイルに書き込めません");
		      }
		    }catch(IOException e){
		    }
	}
	 private static boolean checkBeforeWritefile(File file){
    if (file.exists()){
      if (file.isFile() && file.canWrite()){
        return true;
      }
    }
    return false;
  }
}
