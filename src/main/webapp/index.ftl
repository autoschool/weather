<#-- @ftlvariable name="model" type="ru.yandex.autoschool.weather.models.Weather" -->
<html>
<head>
    <link rel="stylesheet" type="text/css" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    <style>
        body {
            background-color: saddlebrown;
        }

        .fa-xs {
            font-size: 7em;
        }

        .fa-md {
            font-size: 20em;
        }

        .fa-white {
            color: white;
        }

        .city {
            font-size: 3em;
            color: burlywood;
        }

        .temperature {
            font-size: 10em;
            color: white;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-6">
            <i class="fa fa-md fa-cloud fa-white"></i>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6 col-xs-offset-6">
            <div class="city">
                <span class="pull-right">
                <#if model??>
                ${model.getCity()}
                <#else>???</#if>
                </span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6 col-xs-offset-6">
            <div class="temperature">
                <span class="pull-right">
                <#if model??>
                ${model.getHumanizedTemperature()}
                <#else>???</#if>
                </span>
            </div>
        </div>
    </div>
</div>
</body>
</html>