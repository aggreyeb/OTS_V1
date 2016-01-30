using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public  class TestItemOption:Record
    {
        public virtual string Text { get; set; }
        public virtual bool IsCorrect { get; set; }
    }
}
