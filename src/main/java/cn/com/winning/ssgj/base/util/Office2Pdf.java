package cn.com.winning.ssgj.base.util;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Office2Pdf {

	static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。
	static final int wdFormatPDF = 17;// word转PDF 格式
	static final int ppSaveAsPDF = 32;// ppt 转PDF 格式

	static final int WORD_HTML = 8;
	static final int WORD_TXT = 7;
	static final int EXCEL_HTML = 44;

	public static void main(String[] args) {

	}

	public static void word2pdf(String source, String target) {
		ActiveXComponent word = null;
		Dispatch docs = null;
		if (word == null || word.m_pDispatch == 0) {
			word = new ActiveXComponent("Word.Application");
			word.setProperty("Visible", false);
			word.setProperty("DisplayAlerts", false);
		}
		if (docs == null || docs.m_pDispatch == 0) {
			docs = word.getProperty("Documents").toDispatch();
		}
		Dispatch doc = Dispatch.call(docs,//
				"Open", //
				source,// FileName
				false,// ConfirmConversions
				true // ReadOnly
				).toDispatch();

		File tofile = new File(target);
		if (tofile.exists()) {
			tofile.delete();
		}
		Dispatch.call(doc,//
				"SaveAs", //
				target, // FileName
				wdFormatPDF);

		Dispatch.call(doc, "Close", false);
		if (word != null) {
			word.invoke("Quit", wdDoNotSaveChanges);
		}
	}

	public static void ppt2pdf(String source, String target) {
		ActiveXComponent app = null;
		app = new ActiveXComponent("Powerpoint.Application");
		Dispatch presentations = app.getProperty("Presentations").toDispatch();
		Dispatch presentation = Dispatch.call(presentations,//
				"Open", source,// FileName
				true,// ReadOnly
				true,// Untitled 指定文件是否有标题。
				false // WithWindow 指定文件是否可见。
				).toDispatch();

		File tofile = new File(target);
		if (tofile.exists()) {
			tofile.delete();
		}
		Dispatch.call(presentation,//
				"SaveAs", //
				target, // FileName
				ppSaveAsPDF);

		Dispatch.call(presentation, "Close");
		if (app != null) {
			app.invoke("Quit");
		}
	}

	public static void excel2pdf(String source, String target) {
		ActiveXComponent app = new ActiveXComponent("Excel.Application"); // 启动excel(Excel.Application)
		app.setProperty("Visible", false);
		Dispatch workbooks = app.getProperty("Workbooks").toDispatch();
		Dispatch workbook = Dispatch.invoke(workbooks, "Open", Dispatch.Method,
				new Object[] { source, new Variant(false), new Variant(false) }, new int[3]).toDispatch();
		Dispatch.invoke(workbook, "SaveAs", Dispatch.Method, new Object[] { target, new Variant(57),
				new Variant(false), new Variant(57), new Variant(57), new Variant(false), new Variant(true),
				new Variant(57), new Variant(true), new Variant(true), new Variant(true) }, new int[1]);
		Variant f = new Variant(false);
		Dispatch.call(workbook, "Close", f);
		if (app != null) {
			app.invoke("Quit");
		}
	}

	/** * WORD转HTML * @param docfile WORD文件全路径 * @param htmlfile 转换后HTML存放路径 */
	public static void wordToHtml(String docfile, String htmlfile) {
		ActiveXComponent app = new ActiveXComponent("Word.Application");
		// 启动word
		app.setProperty("Visible", new Variant(false));
		Dispatch docs = app.getProperty("Documents").toDispatch();
		Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method,
				new Object[] { docfile, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
		Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { htmlfile, new Variant(WORD_HTML) }, new int[1]);
		Variant f = new Variant(false);
		Dispatch.call(doc, "Close", f);
		if (app != null) {
			app.invoke("Quit");
		}
	}

}