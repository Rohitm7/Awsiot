package delete_files;

import java.io.File;

public class Delete_File {
	public static void Delete_screnshots() {
		String path = "/Users/testingwimate/Desktop/Automation_Screenshots/";
		File file = new File(path);
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			System.out.println(file.getName());
			for (File f : files) {
				if (f.isFile() && f.exists()) {
					// f.delete();
					System.out.println("successfully deleted");
				} else {
					System.out.println("cant delete a file due to open or error");
				}
			}
		}
	}

}
