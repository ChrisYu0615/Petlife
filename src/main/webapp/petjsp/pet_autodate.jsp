<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

				<table class="table caption-top" id="calendar">

					<thead>
						<tr class="tb_header" id="date">
						</tr>
					</thead>
					<tbody>
						<tr class="week" id="week">
						</tr>

						<tr class="button" id="button_row">
						</tr>
					</tbody>

				</table>

				<table class="table caption-top" id="calendar2">

					<thead>
						<tr class="tb_header" id="date2">
						</tr>
					</thead>
					<tbody>
						<tr class="week" id="week2">
						</tr>

						<tr class="button" id="button_row2">
						</tr>
					</tbody>

				</table>
				
				<table class="table caption-top" id="calendar3">

					<thead>
						<tr class="tb_header" id="date3">
						</tr>
					</thead>
					<tbody>
						<tr class="week" id="week3">
						</tr>

						<tr class="button" id="button_row3">
						</tr>
					</tbody>

				</table>
				
				
				<div class=" row">
					<button type="button" class="btn btn-primary update btn-sm"
						onclick="uploadData()" id="upload">上傳</button>
					<button type="button" class="btn btn-warning delete btn-sm" id="rowback">取消</button>

				</div>