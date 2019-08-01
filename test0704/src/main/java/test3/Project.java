package test3;

public class Project {
    private ArticleDao articleDao;
    private String dir;

    public void test() {
        System.out.println("테스트 시작");
        articleDao.insert(articleDao + "insert 됨");
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setArticleDao(OracleArticleDao oracleArticleDao) {
        this.articleDao = oracleArticleDao;
    }
}