//package h2;
//
//import org.h2.tools.Recover;
//
//public class RecoverRunner {
//    public static void main(String[] args) throws Exception {
//        // ここを自分のDBパス・DB名に変更してください
//        String dbDir = "C:/work/pleiades/stu_score/stu/";  // DBファイルがあるディレクトリ
//        String dbName = "scoremanager";                // test.mv.db の場合は "test"
//
//        Recover recover = new Recover();
//        recover.run(new String[] {
//            "-dir", dbDir,
//            "-db", dbName
//        });        System.out.println("Recover 完了: SQLファイルが生成されました。");
//    }
//}
