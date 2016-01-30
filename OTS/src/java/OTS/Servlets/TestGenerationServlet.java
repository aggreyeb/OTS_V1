/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.Servlets;

import OTS.DataModels.DataSource;
import OTS.DataModels.MySqlDataSource;
import OTS.ISerializable;
import OTS.Message;
import OTS.ObjectModels.AcademicTest;
import OTS.ObjectModels.AcademicTestDescription;
import OTS.ObjectModels.AcademicTests;
import OTS.ObjectModels.ConceptRelationMap;
import OTS.ObjectModels.ITestItemGeneration;
import OTS.ObjectModels.QuestionManagement.Questions;
import OTS.ObjectModels.QuestionManagement.TestMarking;
import OTS.ObjectModels.Response;
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
import OTS.ObjectModels.TestQuestionLineItem;
import OTS.ObjectModels.UserProfile;
import OTS.University;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MEA
 */
public class TestGenerationServlet extends  Servlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
   private String AlgorithmKey="AlgorithmKey";
   
   protected ITestItemGeneration FindAlgorithm(HttpServletRequest request,String name){
       
       HttpSession session= request.getSession(false);
       ITestItemGeneration found=null;
         List<ITestItemGeneration> algorithms= (List<ITestItemGeneration>)session.getAttribute(AlgorithmKey);
         for(ITestItemGeneration item:algorithms){
             if(item.HasId(name)){
                 found=item;
                 break;
             }
         }
         
         return found;
   }
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             String command=  this.ExtractRequestCommand(request);
           ISerializable ser=   this.ExecuteCommand(command, request);
           out.println(ser.ToJson());
        }
    }
    
     protected List<ITestItemGeneration> LoadAlgorithm(){
       MySqlDataSource db=  new MySqlDataSource();
       List<ITestItemGeneration> algorithems= new ArrayList();
       algorithems.add(new ListTrueFalseCorrectAlgorithm(db,"ListTrueOrFalseCorrect",new ConceptRelationMap("has","is") ));
       algorithems.add(new ListTrueFalseNegativeAlgorithm(db,"ListTrueOrFalseNegative",new ConceptRelationMap("does not have","is not")));
       algorithems.add(new ListTrueFalseInCorrectAlgorithm(db,"ListTrueOrFalseIncorrect",new ConceptRelationMap("has","is")));
       algorithems.add(new ListTrueFalseNegativeInCorrectAlgorithm(db,"ListTrueOrFalseNegative-Incorrect", new ConceptRelationMap("does not have","is not")));
       
       
       //ListSingleAnswerMultipleChoiceAlgorithm  l= new  ListSingleAnswerMultipleChoiceAlgorithm(db,"ListSingleAnswerMultipleChoice");
       ListSingleAnswerMultipleChoiceAlgorithm  l= new  ListSingleAnswerMultipleChoiceAlgorithm(db,"ListMultipleChoice-SingleAnswerCorrect");
        l.Add(new ListTrueFalseCorrectAlgorithm(db,"ListTrueOrFalseCorrect",new ConceptRelationMap("has","is")));
        l.Add(new ListTrueFalseNegativeAlgorithm(db,"ListTrueOrFalseNegative", new ConceptRelationMap("does not have","is not")));
        l.Add(new ListTrueFalseInCorrectAlgorithm(db,"ListTrueOrFalseIncorrect",new ConceptRelationMap("has","is")));
        l.Add(new ListTrueFalseNegativeInCorrectAlgorithm(db,"ListTrueOrFalseNegative-Incorrect", new ConceptRelationMap("does not have","is not")));
       algorithems.add(l);
       
       ListMultipleAnswersMultipleChoice  lm= new  ListMultipleAnswersMultipleChoice(db,"ListMultipleChoice-MultipleAnwsersCorrect");
       lm.Add(new ListTrueFalseCorrectAlgorithm(db,"ListTrueOrFalseCorrect",new ConceptRelationMap("has","is")));
       lm.Add(new ListTrueFalseInCorrectAlgorithm(db,"ListTrueOrFalseIncorrect",new ConceptRelationMap("has","is")));
       algorithems.add(lm);
       
       
       algorithems.add(new DescribeTrueFalseCorrectAlgorithm(db,"DescribeTrueOrFalseCorrect"));
       algorithems.add(new DescribeTrueFalseNagativeAlgorithm(db,"DescribeTrueOrFalseNegative"));
       algorithems.add(new DescribeTrueFalseIncorrectAlgorithm(db,"DescribeTrueOrFalseInCorrect"));
       algorithems.add(new DescribeTrueFalseNegativeIncorrectAlgorithm(db,"DescribeTrueOrFalseNegative-Incorrect"));
       
     String appendLast1="None of the above statement is true";
     DescribeSingleAnswerMultipleChoiceAlgorithm describeSingleAnswerMultipleChoiceAlgorithm= new DescribeSingleAnswerMultipleChoiceAlgorithm(db,"DescribeMultipleChoice-SingleAnswerCorrect",appendLast1);
     describeSingleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseCorrectAlgorithm(db,"DescribeTrueOrFalseCorrect"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseNagativeAlgorithm(db,"DescribeTrueOrFalseNegative"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseIncorrectAlgorithm(db,"DescribeTrueOrFalseInCorrect"));
     describeSingleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseNegativeIncorrectAlgorithm(db,"DescribeTrueOrFalseNegative-Incorrect"));

     algorithems.add(describeSingleAnswerMultipleChoiceAlgorithm);
     
      
       String appendLast="None of the above statement is true";
       DescribeMultipleAnswerMultipleChoiceAlgorithm describeMultipleAnswerMultipleChoiceAlgorithm= new DescribeMultipleAnswerMultipleChoiceAlgorithm(db,"DescribeMultipleChoice-MultipleAnwsersCorrect",appendLast);
       describeMultipleAnswerMultipleChoiceAlgorithm.Add( new DescribeTrueFalseCorrectAlgorithm(db,"DescribeTrueOrFalseCorrect"));
       describeMultipleAnswerMultipleChoiceAlgorithm.Add(new DescribeTrueFalseIncorrectAlgorithm(db,"DescribeTrueOrFalseInCorrect"));
       algorithems.add(describeMultipleAnswerMultipleChoiceAlgorithm);
       
       algorithems.add(new SummarizeMultipleChoiceAlgorithm(db,"SummarizeMultipleChoice-MultipleAnwsersCorrect"));
       algorithems.add( new ClassifyMultipleChoiceAlgorithm(db,"ClassifyMultipleChoice-MultipleAnwsersCorrect"));
       
       DescribeTrueFalseCorrectAlgorithm describeTrueFalseCorrectAlgorithm=new DescribeTrueFalseCorrectAlgorithm(db,"DescribeTrueOrFalseCorrect");
       algorithems.add(describeTrueFalseCorrectAlgorithm);
       
       DescribeTrueFalseIncorrectAlgorithm describeTrueFalseIncorrectAlgorithm=new DescribeTrueFalseIncorrectAlgorithm(db,"DescribeTrueOrFalseInCorrect");
        algorithems.add(describeTrueFalseIncorrectAlgorithm);
        
        DescribeTrueFalseNagativeAlgorithm describeTrueFalseNagativeAlgorithm= new DescribeTrueFalseNagativeAlgorithm(db,"DescribeTrueOrFalseNegative");
        algorithems.add(describeTrueFalseNagativeAlgorithm);
        
        DescribeTrueFalseNegativeIncorrectAlgorithm describeTrueFalseNegativeIncorrectAlgorithm= new DescribeTrueFalseNegativeIncorrectAlgorithm(db,"DescribeTrueOrFalseNegativeIncorrect");
        algorithems.add(describeTrueFalseNegativeIncorrectAlgorithm);
        
       
        return algorithems;
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected ISerializable ExecuteCommand(String action, HttpServletRequest request) {
          Message response= new Response("","");
          DataSource db=new MySqlDataSource();
        try{
        UserProfile userProfile=this.LoadSession(request);
         switch(action){
              case  "ListTeacherCourse":
               AcademicTests tests=  new AcademicTests( db);
               tests.ListTeacherCourse(userProfile.UserId, response);
                  break;
              case "ListCourseTest":
               int courseId= Integer.parseInt(request.getParameter("CourseId"));
               AcademicTests tests1=  new AcademicTests( db);
               tests1.ListCourseTest(courseId,userProfile.UserId, response);
                  break;
                  
              case "CreateNewTest":  
                  AcademicTest academicTest = new AcademicTest(new AcademicTests( db ));
                   int _courseId= Integer.parseInt(request.getParameter("CourseId"));
                   String description=  request.getParameter("AcademicTest");
                   String startDate=request.getParameter("StartDate");
                           
                   Gson g = new Gson();
                 AcademicTestDescription desc= (AcademicTestDescription) g.fromJson(description, AcademicTestDescription.class);
                 desc.StartDate= new Date(startDate);
                   academicTest.Save(_courseId, userProfile.UserId, desc, response);
                  break;
           
              case "ModifyTest":
                   //DeSerialize  AcademicTestDescription
                    AcademicTest academicTest1 = new AcademicTest(new AcademicTests( db ));
                    String descriptionModify=  request.getParameter("AcademicTest");
                    String startDateModify=request.getParameter("StartDate");
                    Gson gson = new Gson();
                    AcademicTestDescription desc1= (AcademicTestDescription) gson.fromJson(descriptionModify, AcademicTestDescription.class);
                    desc1.StartDate= new Date(startDateModify);
                    academicTest1.Modify(desc1, response);
                  break;   
                  
                 case "DeleteTest":
                   AcademicTest academicTest2 = new AcademicTest(new AcademicTests( db ));
                   int testId= Integer.parseInt(request.getParameter("TestId"));
                   academicTest2.Delete(testId, response);
                  break;   
                     
                  case "ActivateTest":
                    academicTest2 = new AcademicTest(new AcademicTests( db ));
                    testId= Integer.parseInt(request.getParameter("TestId"));
                    academicTest2.Activate(testId, response);
                  break; 
                      
                  case "DeActivateTest":
                    academicTest2 = new AcademicTest(new AcademicTests( db ));
                    testId= Integer.parseInt(request.getParameter("TestId"));
                    academicTest2.DeActivate(testId, response);
                  break;      
                     
                 case "GenerateTestItem":
                   HttpSession session= request.getSession(false);
                    List<ITestItemGeneration> list=  (List<ITestItemGeneration>)session.getAttribute(AlgorithmKey);
                    if(list==null){
                          session.setAttribute(AlgorithmKey, this.LoadAlgorithm());
                    }
                   
                        //Find Algorithm by name
                     String data=request.getParameter("data");
                    
                     TestGenerationInput input= new Gson().fromJson(data, TestGenerationInput.class);
                     ITestItemGeneration gen=   this.FindAlgorithm(request, input.UniqueId);
                     List<TestItemGenerationOutput> result= gen.Generate(input);
                     
                     Questions questions= new Questions(db,response);
                     questions.SaveQTestItems(input, result);
                     
                     String content=new Gson().toJson(result);
                     response.ChangeContent(content);
                     response.ChangeStatus("ok");
                     break;
                  
                 case "ListTestQuestions":
                   AcademicTests tqs = new AcademicTests( db );
                   int tid= Integer.parseInt(request.getParameter("TestId"));
                   int cid= Integer.parseInt(request.getParameter("CourseId"));
                   tqs.ListTestQuestions(userProfile.UserId, cid, tid, response);
                  break;  
                 
                 case "ListTestQuestionLineItems":
                   AcademicTests qqs = new AcademicTests( db );
                   int qd= Integer.parseInt(request.getParameter("QuestionId"));
                   qqs.ListTestQuestionLineItems(qd, response);
                  break;
                    
                  case "UpdateQuestionLineItem":
                   AcademicTests up = new AcademicTests( db );
                   int upqd= Integer.parseInt(request.getParameter("QuestionLineItemId"));
                   String iscorrect= request.getParameter("IsCorrect");
                   Boolean status=false;
                   if(iscorrect.equals("true")){
                       status=true;
                   }
                   up.UpdateQuestionLineItem(upqd,status ,response);
                  break;
                   case "UpdateQuestionLineItemBatch":
                   AcademicTests batch = new AcademicTests( db );
                   String items= request.getParameter("QuestionLineItems");
                   Gson x= new Gson();
                   TestQuestionLineItem[] LineItems=  x.fromJson(items, TestQuestionLineItem[].class);
                   batch.UpdateQuestionLineItem(LineItems ,response);
                  break;
                       
                   case "MarkTest":
                    TestMarking marker = new TestMarking(db);
                    testId= Integer.parseInt(request.getParameter("TestId"));
                    marker.Mark(testId, response);
                  break; 
                       
              default:
                  response.UpdateError("Invalid action");
                  response.UpdateID(0);
                  response.UpdateIdentity("-");
                  break;
           }
           
        }
        catch(Throwable ex){
             response.UpdateError(ex.toString());
             response.ChangeStatus("exception");
             response.ChangeContent("");
        }
        return response; 
    }

}
