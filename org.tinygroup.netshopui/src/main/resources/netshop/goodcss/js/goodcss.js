function showHide(shID) {
   if (document.getElementById(shID)) {
      if (document.getElementById(shID+'Show').style.display != 'none') {
         document.getElementById(shID+'Show').style.display = 'none';
         document.getElementById(shID).style.display = 'block';
      }
      else {
         document.getElementById(shID+'Show').style.display = 'inline';
         document.getElementById(shID).style.display = 'none';
      }
   }
}

