package test3;

public class Project {
    private ArticleDao articleDao;
    private String dir;

    public void test() {
        System.out.println("�׽�Ʈ ����");
        articleDao.insert(articleDao + "insert ��");
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setArticleDao(OracleArticleDao oracleArticleDao) {
        this.articleDao = oracleArticleDao;
    }
}