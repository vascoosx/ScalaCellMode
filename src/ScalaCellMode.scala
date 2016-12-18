import com.intellij.openapi.actionSystem.{ActionManager, AnAction, AnActionEvent, CommonDataKeys}
import com.intellij.openapi.editor.Document


/**
  * Created by Shunichi Otsuka on 2016/12/16.
  */


class ScalaCellMode extends AnAction  {


  def startsWithTripleHash(text: CharSequence, start: Int, end: Int): Boolean = {
    (0 until 3).map(start + _).forall(x => text.charAt(x) == '/')
  }

  def searchForDelimiter(document: Document, caretLineNumber: Int, direction: Int): Int = {
    val lineCount = document.getLineCount
    val text = document.getCharsSequence
    var line = caretLineNumber
    while (line > 0 && line < lineCount){
      line += direction
      val start = document.getLineStartOffset(line)
      val end = document.getLineEndOffset(line)
      (start, end) match {
        case _ if (end - start) < 2 =>
        case _ if startsWithTripleHash(text, start, end) => return line
        case _ =>
      }
    }
    -1
  }

  @Override
  override def actionPerformed(e: AnActionEvent): Unit = {
    val action = ActionManager.getInstance.getAction("Scala.SendSelectionToConsole")
    val context = e.getDataContext
    val editor = CommonDataKeys.EDITOR.getData(context)
    val model = editor.getSelectionModel
    val document = editor.getDocument
    val docCaretOffset = editor.getCaretModel.getOffset
    val caretLineNumber = document.getLineNumber(docCaretOffset)

    val lineUp = searchForDelimiter(document, caretLineNumber, -1)
    val lineDown = searchForDelimiter(document, caretLineNumber, 1)
    val start = document.getLineStartOffset(lineUp + 1)
    val end = document.getLineEndOffset(lineDown - 1)

    if (end - start > 0) {
      model.setSelection(start, end)
    }
    action.actionPerformed(e)

  }
}
