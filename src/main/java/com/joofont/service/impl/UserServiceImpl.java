package com.joofont.service.impl;

import com.joofont.dao.UserMapper;
import com.joofont.entity.User;
import com.joofont.service.UserService;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cui jun on 2018/11/4.
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private Directory dir=null;
    /**
     * 获取IndexWriter实例
     * @return
     * @throws Exception
     */
    private IndexWriter getWriter()throws Exception{
        /**
         * 生成的索引我放在了C盘，可以根据自己的需要放在具体位置
         */
        dir= FSDirectory.open(Paths.get("/Users/cuijun/code/learnProtest/luceneTestData"));
        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
        IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
        IndexWriter writer=new IndexWriter(dir, iwc);
        return writer;
    }

    /**
     * 添加博客索引
     * @param user
     */
    @Override
    public void addIndex(User user) throws Exception {
        IndexWriter writer=getWriter();
        Document doc=new Document();
        doc.add(new StringField("id",String.valueOf(user.getId()), Field.Store.YES));
        /**
         * yes是会将数据存进索引,
         * 如果查询结果中需要将记录显示出来就要存进去，如果查询结果,只是显示标题之类的就可以不用存，而且内容过长不建议存进去
         * 对于需要分词的内容我们使用TextField,对于像id这样不需要分词的内容我们使用StringField
         */
        doc.add(new TextField("name", user.getName(), Field.Store.YES));
        doc.add(new TextField("description",user.getDescription(), Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    /**
     * 查询用户
     * @param q 查询关键字
     * @return
     * @throws Exception
     */
    @Override
    public List<User> searchBlog(String q)throws Exception{
        /**
         * 注意的是查询索引的位置得是存放索引的位置，不然会找不到。
         */
        dir= FSDirectory.open(Paths.get("/Users/cuijun/code/learnProtest/luceneTestData"));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher is=new IndexSearcher(reader);
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
        /**
         * username和description就是我们需要进行查找的两个字段
         * 同时在存放索引的时候要使用TextField类进行存放。
         */
        QueryParser parser=new QueryParser("name", analyzer);
        Query query=parser.parse(q);
        QueryParser parser2=new QueryParser("description", analyzer);
        Query query2=parser2.parse(q);
        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
        TopDocs hits=is.search(booleanQuery.build(), 100);

        // 结果集
        List<User> userList=new LinkedList<>();
        for(ScoreDoc scoreDoc:hits.scoreDocs){
            Document doc=is.doc(scoreDoc.doc);
            User user=new User();
            user.setId(Integer.parseInt(doc.get(("id"))));
            user.setName(doc.get("name"));
            user.setDescription(doc.get("description"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> getAllUserList() {
        return userMapper.getAllUserList();
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }

}
