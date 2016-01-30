

select * from testanswersheet ta 
inner join studenttestanswersheet sta on ta.TestId
inner join testitem q on q.TestItemId=ta.TestItemId
Where sta.StudentId=3 and ta.TestId =1  and ta.A=sta.A and ta.B=sta.B and ta.C=sta.C and ta.D=sta.D ;



/*Correct True false*/
select q.TestItemId,q.QuestionType,q.Mark from testanswersheet ta 
inner join studenttestanswersheet sta on ta.TestId
inner join testitem q on q.TestItemId=ta.TestItemId
Where sta.StudentId=3 and ta.TestId =1 and q.QuestionType=1  and ta.A=sta.A and ta.B=sta.B and ta.C=sta.C and ta.D=sta.D ;


/*InCorrect True false*/
select q.TestItemId,q.QuestionType,q.Mark from testanswersheet ta 
inner join studenttestanswersheet sta on ta.TestId
inner join testitem q on q.TestItemId=ta.TestItemId
Where sta.StudentId=3 and ta.TestId =1 and q.QuestionType=1  and ta.A<>sta.A and ta.B<>sta.B and ta.C<>sta.C and ta.D<>sta.D ;




/*Correct Multiple questions single answer*/
select q.TestItemId,q.QuestionType,q.Mark from testanswersheet ta 
inner join studenttestanswersheet sta on ta.TestId
inner join testitem q on q.TestItemId=ta.TestItemId
Where sta.StudentId=3 and ta.TestId =1 and q.QuestionType=2  and ta.A=sta.A and ta.B=sta.B and ta.C=sta.C and ta.D=sta.D ;


/*InCorrect  Multiple questions single answer*/
select q.TestItemId,q.QuestionType,q.Mark from testanswersheet ta 
inner join studenttestanswersheet sta on ta.TestId
inner join testitem q on q.TestItemId=ta.TestItemId
Where sta.StudentId=3 and ta.TestId =1 and q.QuestionType=2  and ta.A<>sta.A and ta.B<>sta.B and ta.C<>sta.C and ta.D<>sta.D ;



/*Correct Multiple questions multiple answer*/
select q.TestItemId,q.QuestionType,q.Mark from testanswersheet ta 
inner join studenttestanswersheet sta on ta.TestId
inner join testitem q on q.TestItemId=ta.TestItemId
Where sta.StudentId=3 and ta.TestId =1 and q.QuestionType=3 and ta.A=sta.A and ta.B=sta.B and ta.C=sta.C and ta.D=sta.D ;


/*InCorrect  Multiple questions multiple answer*/
select q.TestItemId,q.QuestionType,q.Mark from testanswersheet ta 
inner join studenttestanswersheet sta on ta.TestId
inner join testitem q on q.TestItemId=ta.TestItemId
Where sta.StudentId=3 and ta.TestId =1 and q.QuestionType=3  and ta.A<>sta.A and ta.B<>sta.B and ta.C<>sta.C and ta.D<>sta.D ;













