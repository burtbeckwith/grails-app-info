function initDataTableExportOptions (dataTableValues) {
    var currentDate = new Date();
    var day = currentDate.getDate();
    var month = currentDate.getMonth() + 1;
    var year = currentDate.getFullYear();

    var d_ymd = '' + year + month + day;
    return $(dataTableValues.dataTableName).dataTable({
        "bAutoWidth": false,
        "scrollX": true,
        "bStateSave": true,
        "sTitle" : dataTableValues.fileName,
        "dom": 'T<"wrapper"flipt>',
        "pagingType": "simple",
        "lengthMenu": [[50, -1], [50, "All"]],
        "tableTools": {
            "sSwfPath": dataTableValues.swfPath,
            "sRowSelect": "multi",
            "aButtons": [
                "select_all", "select_none",
                "copy",
                "print",
                {
                    "sExtends": "collection",
                    "sButtonText": "Save",
                    "aButtons": [
                        {
                            "sFileName": "*-" + dataTableValues.dataTableName + "-" + d_ymd + ".csv",
                            "sExtends": "csv"
                        },
                        {
                            "sFileName": "*-" + dataTableValues.dataTableName + "-" + d_ymd + ".xls",
                            "sExtends": "xls"
                        },
                        {
                            "sFileName": "*-" + dataTableValues.dataTableName + "-" + d_ymd + ".pdf",
                            "sExtends": "pdf",
                            "sPdfOrientation": "landscape",
                            "sPdfMessage": dataTableValues.sPdfMessage
                        }
                    ]
                }
            ]
        }
    });

}