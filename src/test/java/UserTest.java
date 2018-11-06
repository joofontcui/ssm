import com.joofont.entity.User;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author cui jun on 2018/11/6.
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml", "classpath:spring/spring-service.xml"})
public class UserTest {

    private static Logger logger = Logger.getLogger(UserTest.class);

    @Test
    public void testAddUser() throws IOException {
        User user = new User();
        user.setId(1);
        user.setName("user测试用例");
        user.setAge(25);
        user.setDescription("随便写的描述");
        user.setPassword("密码");

        // 指定分词技术
        Analyzer analyzer = new StandardAnalyzer();
        // 配置信息
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        // 索引硬盘存储路径
        Directory directory = FSDirectory.open(Paths.get("/Users/cuijun/code/learnProtest/jikeTestData"));
        // 生成的索引文件
        IndexWriter writer = new IndexWriter(directory, indexWriterConfig);

        Document doc = new Document();
        doc.add(new StringField("id",String.valueOf(user.getId()), Field.Store.YES));
        doc.add(new TextField("name", user.getName(), Field.Store.YES));
        doc.add(new TextField("description",user.getDescription(), Field.Store.YES));

        writer.addDocument(doc);
        writer.commit();
        writer.close();
    }

    @Test
    public void testSearchUser() throws IOException, ParseException {
        // 索引硬盘存储路径
        Directory directory = FSDirectory.open(Paths.get("/Users/cuijun/code/learnProtest/jikeTestData"));
        // 读取索引
        DirectoryReader reader = DirectoryReader.open(directory);
        // 创建索引检索对象
        IndexSearcher searcher = new IndexSearcher(reader);
        // 指定分词技术
        Analyzer analyzer = new StandardAnalyzer();
        // 创建query
        QueryParser parser = new QueryParser("name", analyzer);
        QueryParser parser2 = new QueryParser("description", analyzer);

        Query query = parser.parse("写的");
        Query query2 = parser2.parse("写的");

        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);

        // 检索索引，获取结果前10条
        TopDocs topDocs = searcher.search(booleanQuery.build(), 10);

        for(ScoreDoc scoreDoc : topDocs.scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);

            System.out.print("topDocs:"+Integer.parseInt(doc.get(("id")))+doc.get("name")+doc.get("description"));
        }
    }

}
