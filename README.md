# Move-in and Move-out Widget

Use our code to save yourself time on cross-platform, cross-device and cross OS version development and testing
- New widget from the series of solutions for integration with Google Doc. 
This widget will be useful for managing houses, because with its help you can monitor (mark) races / outings of tenants.

# XML Structure declaration
- Colorskin - this is the basic color scheme. Contains 5 elements (color [1-5]). Each widget can set colors for elements of the interface using color scheme in any order, but generally color1-background color, color3-titles color, color4-font color, color5-date or price color.
- About - this is root tag, the screen with the detailed information "About us"
- Logo - company logo
- Title - name of company 
- Description - description of company 
- Propertymap - In the map block for "Property" comes json for the mapping of Google's document columns. 
  Displays the internal name and letter of the column in the Google document. This information is from the first sheet.
- Мovemap - Here is displayed the same json with information on the second sheet
- Google - Root tag, contains information about the document.
- Access_token -  The key of which is a pass to the protected resources. 
- Document_token - Document access key
- Propertyworksheet_id - Id of the first sheet in the document
- Moveworksheet_id - Id of the second sheet in the document
- Propertyfirst_row - The line number from which we begin reading the document in the first sheet
- Movefirst_row - The line number from which we begin reading the document in the second sheet

# Tags:  
       <data>
       <config>
           <colorskin>
              <color1><![CDATA[#c2e793]]></color1>
              <color2><![CDATA[#2d910b]]></color2>
              <color3><![CDATA[#225112]]></color3>
              <color4><![CDATA[#313e20]]></color4>
              <color5><![CDATA[#2d910b]]></color5>
              <color6><![CDATA[rgba(255,255,255,0.2)]]></color6>
              <color7><![CDATA[rgba(255,255,255,0.15)]]></color7>
              <color8><![CDATA[rgba(0,0,0,0.3)]]></color8>
              <isLight><![CDATA[1]]></isLight>
           </colorskin></config>
       <about>
          <logo><![CDATA[http://ibuildapp.com/media/img/widget/moveinmoveout/moveinmoveout_about.png]]></logo>
          <title><![CDATA[BigApp]]></title>
          <description><![CDATA[Test Test]]></description>
       </about>
       <propertymap><![CDATA[{"propertyname":"A","address":"B","flatnumber":"C","monthlyrent":"D"}]]></propertymap>
       <movemap><![CDATA[{"propertyname":"A","flatnumber":"B","tenantName":"C","dateIn":"D","lrDoorsLlocksIn":"E","lrWindowsScreensIn":"F","lrCarpetFlooringIn":"G","drWindowScreensIn":"H","drCarpetFlooringIn":"I","hCarpetFlooringIn":"J","hWwallsIn":"K","hLightsSwitchesIn":"L","kWindowsScreensIn":"M","kFlooringIn":"N","kRefrigeratorIn":"O","kSinkIn":"P","dateOut":"Q","lrDoorsLocksOut":"R","lrWindowsScreensOut":"S","lrCFlooringOut":"T","drWindowScreensOut":"U","drCarpetFlooringOut":"V","hCarpetFlooringOut":"W","hWallsOut":"X","hLightsSwitchesOut":"Y","kWindowsScreensOut":"Z","kFlooringOut":"AA","kRefrigeratorOut":"AB","kSinkOut":"AC"}]]></movemap>
       <google>
         <access_token><![CDATA[ya29.Gl2uBKn4SoIkfJclAmstTzId3d1zwIdUcbFy91X4tA1DMDyj5pCGn4zCcV1q91Y3B_VLQuTDfXfd0pHsEvQYh9kvIB-Oxx7qAQqCBtd608GkvEuevx071fqRT_Y1bqE]]></access_token>
         <document_token><![CDATA[1Z7AWMKv4aj1meKBi793Zpdosq2pkkOJMsu9Ht03GDko]]></document_token>
         <propertyworksheet_id><![CDATA[1772853706]]></propertyworksheet_id>
         <moveworksheet_id><![CDATA[1417023342]]></moveworksheet_id>
         <propertyfirst_row><![CDATA[2]]></propertyfirst_row>
         <movefirst_row><![CDATA[2]]></movefirst_row>
       </google>
       </data>

