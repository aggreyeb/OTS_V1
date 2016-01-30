/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;

import OTS.DataModels.Knowledgemap;
import OTS.DataModels.MySqlDataSource;
import OTS.DataModels.Node;
import OTS.DataModels.NodeItem;
import OTS.ObjectModels.ConceptRelationMap;
import OTS.ObjectModels.ITestItemGeneration;
import OTS.ObjectModels.TestAlgorithms.ClassifyMultipleChoiceAlgorithm;
import OTS.ObjectModels.TestAlgorithms.DescribeMultipleAnswerMultipleChoiceAlgorithm;
import OTS.ObjectModels.TestAlgorithms.DescribeSingleAnswerMultipleChoiceAlgorithm;
import OTS.ObjectModels.TestAlgorithms.DescribeTrueFalseCorrectAlgorithm;
import OTS.ObjectModels.TestAlgorithms.DescribeTrueFalseIncorrectAlgorithm;
import OTS.ObjectModels.TestAlgorithms.DescribeTrueFalseNagativeAlgorithm;
import OTS.ObjectModels.TestAlgorithms.DescribeTrueFalseNegativeIncorrectAlgorithm;
import OTS.ObjectModels.TestAlgorithms.ListMultipleAnswersMultipleChoice;
import OTS.ObjectModels.TestAlgorithms.ListSingleAnswerMultipleChoiceAlgorithm;
import OTS.ObjectModels.TestAlgorithms.ListTrueFalseCorrectAlgorithm;
import OTS.ObjectModels.TestAlgorithms.ListTrueFalseInCorrectAlgorithm;
import OTS.ObjectModels.TestAlgorithms.ListTrueFalseNegativeAlgorithm;
import OTS.ObjectModels.TestAlgorithms.ListTrueFalseNegativeInCorrectAlgorithm;
import OTS.ObjectModels.TestAlgorithms.SummarizeMultipleChoiceAlgorithm;
import OTS.ObjectModels.TestGenerationInput;
import OTS.ObjectModels.TestItemGenerationOutput;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class AlgorithmTest {
    
    @Test
    public void FindKnowledgeMap(){
     int parentConceptNodeId=4;
     ConceptRelationMap rm= new ConceptRelationMap("has","is");
     
      ListTrueFalseCorrectAlgorithm al= new ListTrueFalseCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseCorrect",rm);
    //Act
      Knowledgemap km=  al.Find(parentConceptNodeId);
      
     //Assert
      System.out.print(km.getConcepts());
    }
    
    
     @Test
    public void FindNode(){
     int parentConceptNodeId=85;
      ConceptRelationMap rm= new ConceptRelationMap("has","is");
      ListTrueFalseCorrectAlgorithm al= new ListTrueFalseCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseCorrect",rm);
    //Act
      Node node=  al.FindRootNode(parentConceptNodeId);
      
     //Assert
      System.out.print(node.ToJsonDiscription());
    }
    
     @Test
     public void FindItem(){
     int parentConceptNodeId=85;
      String nodeIdentity="47c9f687-0359-4be6-8df4-f05c17b442ad";
       ConceptRelationMap rm= new ConceptRelationMap("has","is");
      ListTrueFalseCorrectAlgorithm al= new ListTrueFalseCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseCorrect",rm);
    //Act
      Node node=  al.FindRootNode(parentConceptNodeId);
      NodeItem item=al.FindNodeItem(node, nodeIdentity);
     //Assert
      System.out.print(item.Name);
    }
     
     ///Appendix 1
     @Test
     public void GenerateTestItem_ListTrueFalseCorrect(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      ConceptRelationMap rm= new ConceptRelationMap("has","is");
       ListTrueFalseCorrectAlgorithm listTrueFalseCorrectAlgorithm= new ListTrueFalseCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseCorrect",rm);
    //Act
     List<TestItemGenerationOutput> result= listTrueFalseCorrectAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    }
     
     ///Appendix 2
      @Test
     public void GenerateTestItem_ListTrueFalseNegative(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=2;
      ConceptRelationMap rm= new ConceptRelationMap("does not have","is not");
       ListTrueFalseNegativeAlgorithm listTrueFallseNegativeAlgorithm= new ListTrueFalseNegativeAlgorithm(new MySqlDataSource(),"ListTrueOrFalseNegative",rm);
    //Act
     List<TestItemGenerationOutput> result= listTrueFallseNegativeAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    }
     
     
     @Test
     public void GenerateTestItemOnSubNode_ListTrueFalseCorrect(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      //input.NodeParent="47c9f687-0359-4be6-8df4-f05c17b442ad";
      input.NodeIdentity="47c9f687-0359-4be6-8df4-f05c17b442ad";
            ConceptRelationMap rm= new ConceptRelationMap("has","is");
      ListTrueFalseCorrectAlgorithm listTrueFalseCorrectAlgorithm= new ListTrueFalseCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseCorrect",rm);
    //Act
     List<TestItemGenerationOutput> result= listTrueFalseCorrectAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    }
     
     
    @Test
     public void GenerateTestItemOnSubNode_ListTrueFalseInCorrect(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      //input.NodeParent="47c9f687-0359-4be6-8df4-f05c17b442ad";
      input.NodeIdentity="27848d4f-39d9-420e-bdb9-3356d4f18434"; //steam
      ConceptRelationMap rm= new ConceptRelationMap("has","is");
      ListTrueFalseInCorrectAlgorithm listTrueFalseInCorrectAlgorithm= new ListTrueFalseInCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseInCorrect",rm);
    //Act
     List<TestItemGenerationOutput> result= listTrueFalseInCorrectAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    }
     
     
      @Test
     public void GenerateTestItemOnSubNode_ListTrueFalseNegativeInCorrect(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      //input.NodeParent="47c9f687-0359-4be6-8df4-f05c17b442ad";
      input.NodeIdentity="27848d4f-39d9-420e-bdb9-3356d4f18434"; //steam
      ConceptRelationMap rm= new ConceptRelationMap("does not have","is not");
      ListTrueFalseInCorrectAlgorithm listTrueFalseInCorrectAlgorithm= new ListTrueFalseInCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseInCorrect",rm);
    //Act
     List<TestItemGenerationOutput> result= listTrueFalseInCorrectAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    }
     
     
      @Test
     public void GenerateTestItemOnSubNode_ListSingleAnswerMultipleChoice(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=3;
      input.IncludeSubTrees=true;
      input.CourseId=1;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=2;
      input.NatureOfItem=1;
      //input.NodeParent="47c9f687-0359-4be6-8df4-f05c17b442ad";
      input.NodeIdentity="85e91d1c-2a4a-4e43-9698-7c5977824364"; //steam
     
      ListSingleAnswerMultipleChoiceAlgorithm  l= new  ListSingleAnswerMultipleChoiceAlgorithm(new MySqlDataSource(),"ListSingleAnswerMultipleChoice");
      l.Add(new ListTrueFalseCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseCorrect",new ConceptRelationMap("has","is")));
      l.Add(new ListTrueFalseNegativeAlgorithm(new MySqlDataSource(),"ListTrueFalseNagative", new ConceptRelationMap("does not have","is not")));
      l.Add(new ListTrueFalseInCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseIncorrect",new ConceptRelationMap("has","is")));
      l.Add(new ListTrueFalseNegativeInCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseNagativeInCorrect", new ConceptRelationMap("does not have","is not")));
     
      //Act
     List<TestItemGenerationOutput> results= l.Generate(input);
     //Assert
    for(TestItemGenerationOutput item:results ){
        System.out.print(item.toString());  
    }
    
    }
     
     
     
       @Test
     public void GenerateTestItemOnSubNode_ListMultipleAnswersMultipleChoice(){
       TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      //input.NodeParent="47c9f687-0359-4be6-8df4-f05c17b442ad";
      input.NodeIdentity="27848d4f-39d9-420e-bdb9-3356d4f18434"; //steam
     
      ListMultipleAnswersMultipleChoice  l= new  ListMultipleAnswersMultipleChoice(new MySqlDataSource(),"ListMultipleAnswersMultipleChoice");
      l.Add(new ListTrueFalseCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseCorrect",new ConceptRelationMap("has","is")));
      l.Add(new ListTrueFalseInCorrectAlgorithm(new MySqlDataSource(),"ListTrueFalseIncorrect",new ConceptRelationMap("has","is")));
     
      //Act
     List<TestItemGenerationOutput> results= l.Generate(input);
     //Assert
    for(TestItemGenerationOutput item:results ){
        System.out.print(item.toString());  
    }
   }
     
     
      @Test
     public void GenerateTestItemOnSubNode_DescribeTrueFalseCorrect(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      //input.NodeParent="47c9f687-0359-4be6-8df4-f05c17b442ad";
      input.NodeIdentity="27848d4f-39d9-420e-bdb9-3356d4f18434"; //steam
   
      DescribeTrueFalseCorrectAlgorithm describeTrueFalseInCorrectAlgorithm= new DescribeTrueFalseCorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseCorrect");
    //Act
     List<TestItemGenerationOutput> result= describeTrueFalseInCorrectAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    }  
     
     @Test
     public void GenerateTestItemOnSubNode_DescribeTrueFalseNegative(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      //input.NodeParent="47c9f687-0359-4be6-8df4-f05c17b442ad";
      input.NodeIdentity="27848d4f-39d9-420e-bdb9-3356d4f18434"; //steam
   
      DescribeTrueFalseNagativeAlgorithm describeTrueFalseInNegativeAlgorithm= new DescribeTrueFalseNagativeAlgorithm(new MySqlDataSource(),"DescribeTrueFalseNegative");
    //Act
     List<TestItemGenerationOutput> result= describeTrueFalseInNegativeAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    }   
   
       @Test
     public void GenerateTestItemOnSubNode_DescribeTrueFalseNegative2(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      //input.NodeParent="47c9f687-0359-4be6-8df4-f05c17b442ad";
      input.NodeIdentity="245536b6-f984-4ab0-b9f0-8d47a71c4dee"; //root
   
      DescribeTrueFalseNagativeAlgorithm describeTrueFalseInNegativeAlgorithm= new DescribeTrueFalseNagativeAlgorithm(new MySqlDataSource(),"DescribeTrueFalseNegative");
    //Act
     List<TestItemGenerationOutput> result= describeTrueFalseInNegativeAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    } 
     
     
     @Test
     public void GenerateTestItemOnSubNode_DescribeTrueFalseIncorrect(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      //input.NodeParent="47c9f687-0359-4be6-8df4-f05c17b442ad";
      input.NodeIdentity="27848d4f-39d9-420e-bdb9-3356d4f18434"; //steam
   
      DescribeTrueFalseIncorrectAlgorithm describeTrueFalseIncorrectAlgorithm= new DescribeTrueFalseIncorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseIncorrect");
    //Act
     List<TestItemGenerationOutput> result= describeTrueFalseIncorrectAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    }  
     
     @Test
     public void GenerateTestItemOnSubNode_DescribeTrueFalseNegativeIncorrect(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      input.NodeIdentity="245536b6-f984-4ab0-b9f0-8d47a71c4dee"; //Root
   
      DescribeTrueFalseNegativeIncorrectAlgorithm describeTrueFalseNegativeIncorrectAlgorithm= new DescribeTrueFalseNegativeIncorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseNegativeIncorrect");
    //Act
     List<TestItemGenerationOutput> result= describeTrueFalseNegativeIncorrectAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    }   
     
     @Test
     public void GenerateTestItemOnSubNode_DescribeTrueFalseNegativeIncorrect2(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=86;
      input.IncludeSubTrees=true;
      input.CourseId=101;
      input.TestId=2;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      input.NodeIdentity="5f3d18d8-b6af-41d5-a754-49c113fa1b6e"; //Taiwan Cherry
   
      DescribeTrueFalseNegativeIncorrectAlgorithm describeTrueFalseNegativeIncorrectAlgorithm= new DescribeTrueFalseNegativeIncorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseIncorrect");
    //Act
     List<TestItemGenerationOutput> result= describeTrueFalseNegativeIncorrectAlgorithm.Generate(input);
     //Assert
      System.out.print(result.get(0).toString());
    }    
     
       @Test
     public void GenerateTestItemOnSubNode_DescribeSingleAnswerMultipleChoice(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      input.NodeIdentity="27848d4f-39d9-420e-bdb9-3356d4f18434"; 
      String appendLast="None of the above statement is true";
     DescribeSingleAnswerMultipleChoiceAlgorithm describeSingleAnswerMultipleChoiceAlgorithm= new DescribeSingleAnswerMultipleChoiceAlgorithm(new MySqlDataSource(),"DescribeSingleAnswerMultipleChoice",appendLast);
     describeSingleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseCorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseCorrect"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseNagativeAlgorithm(new MySqlDataSource(),"DescribeTrueFalseNegative"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseIncorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseIncorrect"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseNegativeIncorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseNegativeIncorrect"));
//Act
     List<TestItemGenerationOutput> result= describeSingleAnswerMultipleChoiceAlgorithm.Generate(input);
     //Assert
     for(TestItemGenerationOutput s:result){
         System.out.print(s.toString());
     }
     
    }  
   
    @Test
     public void GenerateTestItemOnSubNode_DescribeSingleAnswerMultipleChoice2(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=86;
      input.IncludeSubTrees=true;
      input.CourseId=101;
      input.TestId=2;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      input.NodeIdentity="5f3d18d8-b6af-41d5-a754-49c113fa1b6e"; //Tawian Cherry
      String appendLast="None of the above statement is true";
     DescribeSingleAnswerMultipleChoiceAlgorithm describeSingleAnswerMultipleChoiceAlgorithm= new DescribeSingleAnswerMultipleChoiceAlgorithm(new MySqlDataSource(),"DescribeSingleAnswerMultipleChoice",appendLast);
     describeSingleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseCorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseCorrect"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseNagativeAlgorithm(new MySqlDataSource(),"DescribeTrueFalseNegative"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseIncorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseIncorrect"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseNegativeIncorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseNegativeIncorrect"));
//Act
     List<TestItemGenerationOutput> result= describeSingleAnswerMultipleChoiceAlgorithm.Generate(input);
     //Assert
     for(TestItemGenerationOutput s:result){
         System.out.print(s.toString());
     }
     
    }  
     
     
    @Test
     public void GenerateTestItemOnSubNode_DescribeMultipleAnswerMultipleChoice(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=86;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      input.NodeIdentity="5f3d18d8-b6af-41d5-a754-49c113fa1b6e"; 
      String appendLast="None of the above statement is true";
     DescribeMultipleAnswerMultipleChoiceAlgorithm describeMultipleAnswerMultipleChoiceAlgorithm= new DescribeMultipleAnswerMultipleChoiceAlgorithm(new MySqlDataSource(),"DescribeMultipleAnswerMultipleChoice",appendLast);
     describeMultipleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseCorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseCorrect"));
     describeMultipleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseIncorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseIncorrect"));
   
//Act
     List<TestItemGenerationOutput> result= describeMultipleAnswerMultipleChoiceAlgorithm.Generate(input);
     //Assert
     for(TestItemGenerationOutput s:result){
         System.out.print(s.toString());
     }
     
    }   
     
    
    @Test
     public void GenerateTestItemOnSubNode_DescribeMultipleAnswerMultipleChoice2(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=101;
      input.TestId=2;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      input.NodeIdentity="27848d4f-39d9-420e-bdb9-3356d4f18434"; 
      String appendLast="None of the above statement is true";
     DescribeMultipleAnswerMultipleChoiceAlgorithm describeMultipleAnswerMultipleChoiceAlgorithm= new DescribeMultipleAnswerMultipleChoiceAlgorithm(new MySqlDataSource(),"DescribeMultipleAnswerMultipleChoice",appendLast);
     describeMultipleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseCorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseCorrect"));
     describeMultipleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseIncorrectAlgorithm(new MySqlDataSource(),"DescribeTrueFalseIncorrect"));
   
//Act
     List<TestItemGenerationOutput> result= describeMultipleAnswerMultipleChoiceAlgorithm.Generate(input);
     //Assert
     for(TestItemGenerationOutput s:result){
         System.out.print(s.toString());
     }
     
    }   
      
    
      @Test
     public void GenerateSummarizeMultipleChoiceAlgorithm(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=2;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      input.NodeIdentity=""; 
     SummarizeMultipleChoiceAlgorithm summarizeMultipleChoiceAlgorithm= new SummarizeMultipleChoiceAlgorithm(new MySqlDataSource(),"SummarizeMultipleChoice");
    
//Act
     List<TestItemGenerationOutput> result= summarizeMultipleChoiceAlgorithm.Generate(input);
     //Assert
     for(TestItemGenerationOutput s:result){
         System.out.print(s.toString());
     }
     
    }   
      
     
    @Test
     public void GenerateSummarizeMultipleChoiceAlgorithm2(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=85;
      input.IncludeSubTrees=true;
      input.CourseId=100;
      input.TestId=1;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      //input.NodeParent="47c9f687-0359-4be6-8df4-f05c17b442ad";
      input.NodeIdentity="27848d4f-39d9-420e-bdb9-3356d4f18434"; //steam
   
     SummarizeMultipleChoiceAlgorithm summarizeMultipleChoiceAlgorithm= new SummarizeMultipleChoiceAlgorithm(new MySqlDataSource(),"SummarizeMultipleChoice");
    
//Act
     List<TestItemGenerationOutput> result= summarizeMultipleChoiceAlgorithm.Generate(input);
     //Assert
     for(TestItemGenerationOutput s:result){
         System.out.print(s.toString());
     }
     }
     
     
     @Test
     public void GenerateTestItemOnSubNode_ClsssifySingleAnswerMultipleChoice(){
      TestGenerationInput input= new TestGenerationInput();
      input.KnowledgeMapId=86;
      input.IncludeSubTrees=true;
      input.CourseId=101;
      input.TestId=2;
      input.CognitiveType=1;
      input.ItemType=1;
      input.NatureOfItem=1;
      input.NodeIdentity="5f3d18d8-b6af-41d5-a754-49c113fa1b6e"; //Tawian Cherry
      ClassifyMultipleChoiceAlgorithm classifyMultipleChoiceAlgorithm= new ClassifyMultipleChoiceAlgorithm(new MySqlDataSource(),"ClassifyMultipleChoice");
     
//Act
     List<TestItemGenerationOutput> result= classifyMultipleChoiceAlgorithm.Generate(input);
   
     //Assert
     for(TestItemGenerationOutput s:result){
         System.out.print(s.toString());
     }
     
    }   
     @Test
     public void FindAlgorithmByName(){
         String name="DescribeTrueFalseNegativeIncorrect";
         
         List<ITestItemGeneration> algorithms=LoadAlgorithm();
         for(ITestItemGeneration a: algorithms){
             if(a.HasId(name)){
                  System.out.print("found");
                  break;
             }
            
         }
     }
     
     
      protected List<ITestItemGeneration> LoadAlgorithm(){
       MySqlDataSource db=  new MySqlDataSource();
       List<ITestItemGeneration> algorithems= new ArrayList();
       algorithems.add(new ListTrueFalseCorrectAlgorithm(db,"ListTrueOrFalseCorrect",new ConceptRelationMap("has","is") ));
       algorithems.add(new ListTrueFalseNegativeAlgorithm(db,"ListTrueOrFalseNegative",new ConceptRelationMap("does not have","is not")));
       algorithems.add(new ListTrueFalseInCorrectAlgorithm(db,"ListTrueOrFalseIncorrect",new ConceptRelationMap("has","is")));
       algorithems.add(new ListTrueFalseNegativeInCorrectAlgorithm(db,"ListTrueOrFalseNegative-Incorrect", new ConceptRelationMap("does not have","is not")));
       
       ListSingleAnswerMultipleChoiceAlgorithm  l= new  ListSingleAnswerMultipleChoiceAlgorithm(db,"ListSingleAnswerMultipleChoice");
        l.Add(new ListTrueFalseCorrectAlgorithm(db,"ListTrueFalseCorrect",new ConceptRelationMap("has","is")));
        l.Add(new ListTrueFalseNegativeAlgorithm(db,"ListTrueFalseNegative", new ConceptRelationMap("does not have","is not")));
        l.Add(new ListTrueFalseInCorrectAlgorithm(db,"ListTrueFalseIncorrect",new ConceptRelationMap("has","is")));
        l.Add(new ListTrueFalseNegativeInCorrectAlgorithm(db,"ListTrueFalseNegative-Incorrect", new ConceptRelationMap("does not have","is not")));
       algorithems.add(l);
       
       ListMultipleAnswersMultipleChoice  lm= new  ListMultipleAnswersMultipleChoice(db,"ListMultipleAnswerMultipleChoice");
       lm.Add(new ListTrueFalseCorrectAlgorithm(db,"ListTrueFalseCorrect",new ConceptRelationMap("has","is")));
       lm.Add(new ListTrueFalseInCorrectAlgorithm(db,"ListTrueFalseIncorrect",new ConceptRelationMap("has","is")));
       algorithems.add(lm);
       
       
       algorithems.add(new DescribeTrueFalseCorrectAlgorithm(db,"DescribeTrueFalseCorrect"));
       algorithems.add(new DescribeTrueFalseNagativeAlgorithm(db,"DescribeTrueFalseNegative"));
       algorithems.add(new DescribeTrueFalseIncorrectAlgorithm(db,"DescribeTrueFalseInCorrect"));
       algorithems.add(new DescribeTrueFalseNegativeIncorrectAlgorithm(db,"DescribeTrueFalseNegativeIncorrect"));
       
     String appendLast1="None of the above statement is true";
     DescribeSingleAnswerMultipleChoiceAlgorithm describeSingleAnswerMultipleChoiceAlgorithm= new DescribeSingleAnswerMultipleChoiceAlgorithm(db,"DescribeSingleAnswerMultipleChoice",appendLast1);
     describeSingleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseCorrectAlgorithm(db,"DescribeTrueFalseCorrect"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseNagativeAlgorithm(db,"DescribeTrueFalseNegative"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseIncorrectAlgorithm(db,"DescribeTrueFalseInCorrect"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseNegativeIncorrectAlgorithm(db,"DescribeTrueFalseNegativeIncorrect"));

     algorithems.add(describeSingleAnswerMultipleChoiceAlgorithm);
     
      
       String appendLast="None of the above statement is true";
       DescribeMultipleAnswerMultipleChoiceAlgorithm describeMultipleAnswerMultipleChoiceAlgorithm= new DescribeMultipleAnswerMultipleChoiceAlgorithm(db,"DescribeMultipleAnswerMultipleChoice",appendLast);
       describeMultipleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseCorrectAlgorithm(db,"DescribeTrueFalseCorrect"));
       describeMultipleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseIncorrectAlgorithm(db,"DescribeTrueFalseInCorrect"));
       algorithems.add(describeMultipleAnswerMultipleChoiceAlgorithm);
       
       algorithems.add(new SummarizeMultipleChoiceAlgorithm(db,"SummarizeMultipleChoice"));
       algorithems.add( new ClassifyMultipleChoiceAlgorithm(db,"ClassifyMultipleChoice"));
       
      
       return algorithems;
    }
     
     
}
