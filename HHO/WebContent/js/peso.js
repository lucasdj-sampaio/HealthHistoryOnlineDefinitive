var itensRow = [];

function loadModalData (itemIndex) {
  let dateSplited = itensRow[itemIndex].children[2].innerText.split('/');
  let dateConvert = `${dateSplited[2]}-${dateSplited[1]}-${dateSplited[0]}`;

  document.getElementById('iptId').value = itemIndex;
  document.getElementById('iptPeso').value = itensRow[itemIndex].children[1].innerText.replace('Kg','');
  document.getElementById('iptData').value = dateConvert;

  openModal('#atualizarPeso');

  console.log(itensRow[itemIndex].children[1].innerText)
}

window.onload = () => {
  itensRow = document.getElementsByClassName('line-w');
}