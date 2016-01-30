using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public class QuestionLineItem:Record 
    {
        public virtual int QuestionLineItemId { get; set; }
        public virtual string Text { get; set; }
        public virtual bool IsCorrect { get; set; }
    }
}
