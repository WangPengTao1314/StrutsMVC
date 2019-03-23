<%@ page contentType="text/html;charset=utf-8" %>
<%	

%>
<!-- 功能条 按钮重写！！ xing_kefa 2012-01-12 -->
<table>
		<tbody>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td>
					<div noWrap="nowrap" style="font-family: 宋体; font-size: 13px;">
					<a class="ICOhover" href="#" onClick="report1_saveAsExcel();return false;">
						<input  type='button' class='btn' value='导出excel'></a>
					<a class="ICOhover" href="#" onClick="report1_saveAsPdf();return false;">
						<input  type='button' class='btn' value='导出pdf'></a>
					<a class="ICOhover" href="#" onClick="report1_saveAsWord();return false;">
						<input  type='button' class='btn' value='导出word'></a>
					<a class="" href="#" onClick="report1_directPrint();return false;">
						<input  type='button' class='btn' value='直接打印'></a>
					<a class="ICOhover" href="#" onClick="report1_print();return false;">
						<input  type='button' class='btn' value='打印预览'></a>					
					<span id="runqian_submit" style="cursor: pointer;" onclick="_submitTable( report1 );return false;">
						<input class="btn" type="button" value="保 存"/>
					</span>	
					</div>
				</td>
			</tr>
		</tbody>
</table>
