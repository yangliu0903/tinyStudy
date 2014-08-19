/********** 省级数据 **********/
var GP =['江苏省'];
/********** 市级数据 **********/
var GT = [
	['南京市','盐城市','连云港市','南通市','无锡市','徐州市','镇江市','宿迁市','淮安市','扬州市','常州市','苏州市','泰州市']
	];
/********** 市二级数据 **********/
var GC =[
	[

            ['南京      迈皋桥票务中心                王    莉      85642281',
             '南京      鼓楼票务中心                     潘雅婷      83797052',
             '江宁      江宁东山支局                     杨     骏      13585125511',
             '六合      六合雄州镇支局                徐卫东      13913355091',
             '浦口      浦口珠江支局                     黄文玲      13327726100',
             '高淳      高淳通贤路支局                孙     辉      15335155598',
             '溧水      溧水中大街支局                程旭纪      13813099008'],

             ['盐城      通榆路支局                          郭乃洁      88261236',
              '盐城      新区邮政所                          邓振强      88122890',
              '盐城      开发区邮政所                     韩     俊      88282802',
              '盐城      票务中心                               顾德梅      83103966',
              '东台      东亭路支局                          杨冠华      85289395',
              '大丰      大丰电子商务                     朱亚兵      83515288',
              '射阳      振阳营业厅                          印永权      82398338',
              '建湖      建湖路支局                          王卫兵      86213525',
              '阜宁      城中支局                               魏广亚      18921801005',
              '滨海      阜东邮政营业厅                屠建林      051584222801',
              '响水      城东支局                               高中山      86862414'],

            ['连云港      海连路支局                     陆习志      13815666566',
             '连云港      海州支局                          李训光      051885431409',
             '连云港      墟沟支局                          李     斌      051882311680',
             '连云港      连云港票务中心           霍如勇      13851264455',
             '赣榆      赣榆黄海路支局                刘     冬      051886221645',
             '东海      东海幸福路支局                黄     燕      051887212543',
             '灌云      灌云胜利路支局                汪     星      13675266113',
             '灌南      灌南人民西路支局           李树芹      051883211794'],

            ['南通      南通端平桥票务中心      韩     力      85115094',
             '南通      南通11185票务中心       傅建华      85115102',
             '南通      南通新开支局                     陆宇星     83596464',
             '南通      南通百安谊家支局           陈     斌      81067790',
             '如皋      如皋邮政票务中心           陈     峰      87517233',
             '如东      如东邮局电子商务部      陈新平      84199912',
             '通州      通州邮政票务中心           施榴卉      86104668',
             '海门      海门邮政票务中心           袁     柳      82211225',
             '启东      启东邮政票务中心           蔡伟杰      13806286800',
             '海安      海安通海票务中心           储     建      88819007'],

            ['无锡      无锡电子商务局                许雪丽      13861827979',
             '无锡      无锡11185          沈     钰      13961808879',
             '宜兴      宜兴邮政11185      周     伟      0510-11185',
             '宜兴      宜兴和桥邮政支局           王新洪      87801441',
             '江阴      江阴11185          王存俭      86280813',
             '江阴      江阴顾山支局                   陆振东      86321531',
             '无锡      市局票务中心                    丁克萍      80800185-2301',
             '无锡      东亭支局                             陈海洲      13921162266',
             '无锡      东降支局                              华亚灵      13915337900',
             '无锡      新区支局                               叶     坚      13771562300',
             '无锡      钱桥支局                             朱幽枫      13961818178'],

            ['徐州      徐州电信台席                    徐     妍      83813185',
             '徐州      徐州建国路旗舰店          刘     艳      83803185',
             '邳州      邳州市解放路支局          龚培友      13585362382',
             '丰县      丰县向阳路支局               吴庆真      89223073',
             '新沂      新沂市市府路支局          赵     君      88934787',
             '睢宁      睢宁县新市街支局          郭铁军      86776699',
             '沛县      沛县中心支局                    候春娜      89635757'],

            ['镇江      镇江市解放路支局           高志远      0511-85026043',
             '镇江      镇江市江苏大学支局      赵亚文      0511-88782380',
             '丹阳      丹阳市开发区支局           刘嘉琳      13806101999',
             '扬中      扬中市长旺支局                何     峰      18952909332',
             '句容      中街支局                               董红芳      0511-87223841'],

            ['宿迁      珠江路支局                          侍雷雷      0527-84459125',
             '宿迁      宿迁南湖路支局                王     婷      0527-84359183内线6362',
             '宿迁      宿迁11185票务中心        张咏梅      0527-84359111',
             '沭阳      沭阳城中支局                      葛志林      0527－83551510',
             '泗阳      泗阳邮政营业班                 许     多      0527-88502599',
             '泗洪      泗洪城中支局                      张青春      0527-88210080'],

            ['淮安      市广场票务中心                 章建平      83933283',
             '洪泽      洪泽人民路支局                李     峰      13852311688',
             '楚州      楚州镇淮楼支局                杨善章      15952350055',
             '涟水      涟水南园支局                     叶虎成      6804',
             '盱眙      盱眙斩龙涧支局                张培军      13852495088',
             '金湖      金湖三里桥支局                吴艳红      15161720808',
             '淮阴      淮阴区西马路支局           张德娟      13905239291'],

            ['扬州      11185              许     萍      0514-87863976',
             '扬州      三元桥支局                          王     林      0514-87368186',
             '宝应      白田支局                               查建华      13852501789',
             '江都      东方红路支局                     王忠礼      13801443860',
             '高邮      文游营业厅                          孙高霞      0514-84615335',
             '仪征      鼓楼支局                               魏     明      0514-83419045',
             '胥浦      万年支局                               李     磊      0514-83211196'],

            ['常州      185支局                                 庄迎春      88119589',
             '常州      前黄支局                               钱争鸣      6511174',
             '常州      卜弋支局                               虞翔贞      3317141',
             '常州      泰山支局                               万丽芬      85609195',
             '常州      兰陵支局                               雍     健      6642865',
             '溧阳      溧阳邮政营业局                钱恩名      87289885',
             '金坛      金坛东环支局                     张立平      82323185'],

            ['苏州      邮电综合营业处                钱     静      051268635256',
             '张家港      杨舍西街邮政营业处',
             '吴江      梅堰支局                                                    0512-63681328',
             '常熟      金沙江路邮政支局',
             '太仓      新塘邮政支局                                          0512-53621205',
             '昆山      蓬朗支局'],

            ['泰州      电子商务局                          张习兰      13615194605',
             '泰州      迎春西路邮政支局           崔     霞      13912191096',
             '泰州      东进西路邮政支局           沈如平      13179424565',
             '泰兴      国庆路邮政                          赵     文      13952658756',
             '姜堰      姜堰坝口广场票务中心      王正德      13805238798',
             '靖江      车站邮政                               唐     亮      15961060908',
             '兴化      戴南支局                               余洪锦      15861061188']
      ]
]