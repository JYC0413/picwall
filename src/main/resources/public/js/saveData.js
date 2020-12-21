$('.saveData').click(function () {
            //#proMain:要截图的DOM元素
            //useCORS:true:解决跨域问题
            html2canvas(document.querySelector('#proMain'),{useCORS:true}).then(function (canvas) {
                //获取年月日作为文件名
                var timers=new Date();
                var fullYear=timers.getFullYear();
                var month=timers.getMonth()+1;
                var date=timers.getDate();
                var randoms=Math.random()+'';
                //年月日加上随机数
                var numberFileName=fullYear+''+month+date+randoms.slice(3,10);
                var imgData=canvas.toDataURL();
                //保存图片
                var saveFile = function(data, filename){
                    var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
                    save_link.href = data;
                    save_link.download = filename;
 
                    var event = document.createEvent('MouseEvents');
                    event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
                    save_link.dispatchEvent(event);
                };
                //最终文件名+文件格式
                var filename = numberFileName + '.png';
                saveFile(imgData,filename);
                //document.body.appendChild(canvas);  把截的图显示在网页上
            })
        }