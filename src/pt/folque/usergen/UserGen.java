package pt.folque.usergen;

import java.io.File;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class UserGen {

	public final static void main(String[] args){

		if(args.length > 0){
			int limit = Integer.parseInt(args[0]);
			String header = "INSERT INTO `ebiz`.`user`(`id`,`email`,`password`,`role`)VALUES\n";
			File sqlFile = new File("userList.sql");
			File csvFile = new File("userList.csv");

			try {
				sqlFile.createNewFile();
				csvFile.createNewFile();
				StringBuilder sqlSb = new StringBuilder();
				StringBuilder csvSb = new StringBuilder();

				sqlSb.append(header);
				for(int i = 0; i < limit; i++){
					String newUserSql = "(null,"
							+ "'user" + (i+1) + "@email.com',"
							+ "'494414ded24da13c451b13b424928821351c78fce49f93d9e1b55f102790c206',"
							+ "'user'),\n";

					String newUserCsv = "user" + (i+1) + "@email.com,lala,user\n";
					sqlSb.append(newUserSql);
					csvSb.append(newUserCsv);
					if( (i+1) == limit){
						sqlSb.replace(sqlSb.length() - 2, sqlSb.length() - 1, ";");
					}
				}
				Files.write(sqlSb, sqlFile, Charsets.UTF_8);
				Files.write(csvSb, csvFile, Charsets.UTF_8);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
			System.err.println("You're missing one attribute (number of users)");
		}
	}
}
