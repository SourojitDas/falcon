package models.tests

import models.googleMaps.*
import org.junit.Test


class GoogleRouteTest {


    val fakeGoogleRouteResp = "{\n" +
            "   \"geocoded_waypoints\" : [\n" +
            "      {\n" +
            "         \"geocoder_status\" : \"OK\",\n" +
            "         \"place_id\" : \"ChIJa147K9HX3IAR-lwiGIQv9i4\",\n" +
            "         \"types\" : [ \"amusement_park\", \"establishment\", \"point_of_interest\" ]\n" +
            "      },\n" +
            "      {\n" +
            "         \"geocoder_status\" : \"OK\",\n" +
            "         \"place_id\" : \"ChIJzzgyJU--woARcZqceSdQ3dM\",\n" +
            "         \"types\" : [ \"amusement_park\", \"establishment\", \"point_of_interest\" ]\n" +
            "      }\n" +
            "   ],\n" +
            "   \"routes\" : [\n" +
            "      {\n" +
            "         \"bounds\" : {\n" +
            "            \"northeast\" : {\n" +
            "               \"lat\" : 34.1373841,\n" +
            "               \"lng\" : -117.9220826\n" +
            "            },\n" +
            "            \"southwest\" : {\n" +
            "               \"lat\" : 33.8151707,\n" +
            "               \"lng\" : -118.3575556\n" +
            "            }\n" +
            "         },\n" +
            "         \"copyrights\" : \"Map data ©2019 Google\",\n" +
            "         \"legs\" : [\n" +
            "            {\n" +
            "               \"distance\" : {\n" +
            "                  \"text\" : \"36.0 mi\",\n" +
            "                  \"value\" : 57961\n" +
            "               },\n" +
            "               \"duration\" : {\n" +
            "                  \"text\" : \"50 mins\",\n" +
            "                  \"value\" : 3025\n" +
            "               },\n" +
            "               \"end_address\" : \"100 Universal City Plaza, Universal City, CA 91608, USA\",\n" +
            "               \"end_location\" : {\n" +
            "                  \"lat\" : 34.1364887,\n" +
            "                  \"lng\" : -118.3569926\n" +
            "               },\n" +
            "               \"start_address\" : \"1313 Disneyland Dr, Anaheim, CA 92802, USA\",\n" +
            "               \"start_location\" : {\n" +
            "                  \"lat\" : 33.8162219,\n" +
            "                  \"lng\" : -117.9224731\n" +
            "               },\n" +
            "               \"steps\" : [\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"59 ft\",\n" +
            "                        \"value\" : 18\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 2\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 33.8160679,\n" +
            "                        \"lng\" : -117.9225314\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Head \\u003cb\\u003esouth\\u003c/b\\u003e\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"kvkmElvvnU\\\\J\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 33.8162219,\n" +
            "                        \"lng\" : -117.9224731\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.4 mi\",\n" +
            "                        \"value\" : 569\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"2 mins\",\n" +
            "                        \"value\" : 129\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 33.8151707,\n" +
            "                        \"lng\" : -117.9280369\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Keep \\u003cb\\u003eleft\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"keep-left\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"mukmExvvnUH@H@JDJHNJJJPTN\\\\Lb@B`@?h@@v@DdYZ?R?\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 33.8160679,\n" +
            "                        \"lng\" : -117.9225314\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.6 mi\",\n" +
            "                        \"value\" : 1028\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"2 mins\",\n" +
            "                        \"value\" : 111\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 33.8200876,\n" +
            "                        \"lng\" : -117.9228559\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"turn-left\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"yokmEfywnUAyW?u@@k@Cg@EUO]KSOSIOKQOOQK]U[Mm@Q}@Ug@Km@Ig@Ec@A[?U@U@U@[DA?WBG@g@HkAZeAVkBb@_AV_@N\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 33.8151707,\n" +
            "                        \"lng\" : -117.9280369\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"230 ft\",\n" +
            "                        \"value\" : 70\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 6\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 33.8206848,\n" +
            "                        \"lng\" : -117.9230995\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Merge onto \\u003cb\\u003eDisneyland Dr\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"merge\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"qnlmEzxvnUG@SFEBsA`@\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 33.8200876,\n" +
            "                        \"lng\" : -117.9228559\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"22.3 mi\",\n" +
            "                        \"value\" : 35894\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"26 mins\",\n" +
            "                        \"value\" : 1537\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 34.0256221,\n" +
            "                        \"lng\" : -118.2059515\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Slight \\u003cb\\u003eright\\u003c/b\\u003e to merge onto \\u003cb\\u003eI-5 N\\u003c/b\\u003e toward \\u003cb\\u003eLos Angeles\\u003c/b\\u003e\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"grlmEjzvnUu@MKCIAWI_Cy@WGSGMEQEKCYCMAU?a@@E@E?MBE@I@IBKBWLE@EBIDGDKF}ArAeFlEWTs@l@qAjA_@\\\\]p@}ApAg@b@iAdA}BbCSTYXmExEsAxAOP[^cBlBw@x@qBtB}@~@{B`Cs@x@KLEBEDSPGF_BhCa@r@Wh@Wf@Sf@c@jAOb@K^ITW`AMd@_@xAWnAU`Bm@jEi@`Eu@hFOv@GXWlAMh@e@|AM`@M^KX{@vBkJtSmKhVgB|DiFlLeFfLWl@Q^Uf@_@t@a@r@a@r@A@Yd@y@zA}@|AQZYd@[h@U`@QZ[l@c@|@_@v@{@rBUn@s@hB_BjE_BtDuEhLu@hB[t@aChGQf@{CjHcDtHiItRkAdCuAdCy@rAgA~Aa@l@aB|BGHgAtAu@dAeGlIe@z@?@A@IPKRA@?@GJSd@_@~@Qh@Qh@WdAADIb@YrAe@~BGREVSbAU~@kAlE_A|Eg@zBADWbAOn@}@hCQh@k@~AcB~DYp@Wh@qAjCc@x@yBvD_BpC[f@eAfBgChFaBrDc@`AyHhQsAbDmAjCKV]v@uBxEuBvEoBnEsAnCABGHGNINEJCJGJGRc@`A[t@c@`AoAlCmBjEMZ]t@_@z@w@bBe@|@c@r@OTOTU\\\\g@v@a@h@g@n@e@j@Y^QRi@l@_@b@MPA?_@f@GFw@`AiBjCe@r@S^a@t@Ub@g@fACDq@xAm@tAqAxCiBbEg@lAcAzBYl@Wn@GJIRaBxDuCvGuEnK_ArBiBbE}@rBcAzBqBtEkBdEqA|CiAtBaD`GuAhCgAzBo@xAw@rBk@~Aq@xAcA|BoBtDYh@GJGLMVGNsA`Dc@bASj@y@rBcB|D_B~Da@bAsAjDyDjJi@nAm@vAsA`DmB`FIPwC~GiF|KaAlBmBvD{AzC{GzMkCpFsKhUkAhCcArCkBfEoBjEaAhBmAzBa@t@a@t@mAlBmAlBaB~BiBbCi@p@m@r@mBtBaChCmChCEBwBpBkDlDKLmBlBgBbBsAnAeAdAQVi@n@kA~A{@dA_@h@e@z@Yf@[l@e@~@sAvCw@|AIPm@vAq@|AiAfCcAhBq@hAg@p@gBnCcDlE{D`EkAbAeAx@QJYVYVgDzBw@f@w@d@{CnBmAx@IDwA~@OJQJEBQJCB]NqD|BiBhAoFjDqW`P{D|BsEtCg@Xg@Ze@XcBlA_K`HgA|@eDlBoG`EcAn@SLm@^wAz@a@Ve@ZiEdCqAt@wBnAGBoBjA}BrAKFGDu@b@k@Xi@XWNcB`Aa@VoDvBwCbBcAn@}@h@yBlAeAj@[RMF_@Ru@b@GDm@\\\\yAz@w@b@eAn@q@`@qEhC{A~@cDjBkAp@cAl@_FtC_@T_@T_Ap@y@n@g@b@iAfAa@`@UXmAvAa@j@QVwAvBk@|@}A`CeA`B{BhDeA~A_@l@iAfB}@rAeEpGo@bAMPSZCBuAvByBhD_DzEwPrWSXSZg@n@SVe@j@_@^[\\\\kAdAe@\\\\UNOLw@h@}@j@sAr@e@TQF[Lq@Vs@R_B`@iE|@WFqDr@sAZkATcB^s@NcB\\\\a@JaAP}A\\\\c@Jg@JgAZi@Nu@XiAd@gAh@eAl@MHKFe@TGDSL{@h@i@^s@j@cAr@}B~A[PeAr@mClB_@\\\\cAz@qAnAoApA_A|@{A|AYX_A~@}@|@yBxBuBtBoBpBmAlA{AzAeBdBkAhAeAhAg@f@KLOPs@|@a@n@QXq@fA{@vAy@tA]l@[b@w@pA[b@a@d@y@|@w@v@YT?@CBKJA?ON]\\\\kClCmAlA}@t@{@v@A@wEjEoJbJeJ|IaC|B]Zc@^ED]Zo@n@q@l@MNc@\\\\o@d@m@`@cAj@aAh@q@Xq@Zq@^i@ZC@k@\\\\cAt@}@t@sBnByCpCkB`B}AzA_CvB}AvAu@r@cA`AoAtAkMtOwAbBa@r@[d@i@|@i@fAaAnBo@fASZQVcAnAeAnAi@h@UTs@l@mD|C_@\\\\MJYVg@f@g@j@e@p@[f@W^k@dAy@lBeAxCYr@w@zBaAtC{GpRcA|CYfAW~A}@nECRk@~B]`Ae@hAg@dAq@pAOXIPA@A@Ud@MXGNGNEJ[t@ADADi@~ASl@Of@Wt@cAbDaAnCkCrHa@hAA@GNq@lBe@vAe@dBWbACLMt@m@vDOrAKrAGjAEdBCbC@|J@zM?lB?nE?rEAbDCv@EhAA`@C`@It@MrAOhAMz@QfAKh@Mf@Qt@Of@W|@Sp@wBpGiAfD{@jCkEtMaH|S_@fAMb@_@rAa@zAI\\\\M`@\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 33.8206848,\n" +
            "                        \"lng\" : -117.9230995\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"11.7 mi\",\n" +
            "                        \"value\" : 18809\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"17 mins\",\n" +
            "                        \"value\" : 1022\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 34.1294658,\n" +
            "                        \"lng\" : -118.3475583\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Keep \\u003cb\\u003eleft\\u003c/b\\u003e at the fork to continue on \\u003cb\\u003eUS-101 N\\u003c/b\\u003e, follow signs for \\u003cb\\u003eLos Angeles N\\u003c/b\\u003e/\\u003cb\\u003eCivic Center\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"fork-left\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"cstnEdbnpU@d@?@?@?@CJm@fCW`AMf@Od@Ut@cArCe@xAq@lB?@u@vBkD|Jq@nBc@rASh@e@vAYx@Wp@q@rB]bAm@`BUj@a@jAWp@w@hBOZS`@[p@INCFGJOVOX]j@]h@MTg@r@m@x@e@j@KLGDABC@C@SHiAhAA?EDw@x@[Zs@h@m@`@]T]RQFa@RSJQFMDiAXa@LWDg@FOB]Dy@Du@@u@As@EoBWy@Gu@Ek@?MAQ?_@@[BmAFy@Fa@@U@a@?W?a@Aa@Ca@C]Cm@KWCKCmASyC]}CU]Ak@Ae@?aEBoC@k@?}AFi@@uAJk@Di@Hm@Fm@JyAXi@LcBd@_A\\\\c@PsAh@UHeAh@_@L]Nc@Pc@Pe@R}@\\\\{An@WNUNo@f@MN[^QVGJAB[j@[z@A??BUp@CLAHCJAHCP?HCNAPARAb@Er@?HAF?DAJAJG^AJC^AFATE\\\\OnAU~BKhAEZC`@CNMrAKpA_@pESnCInAK|ACRATClAARCVEZIdAGbA?BCZEbACj@CdAAz@@\\\\@NBb@Br@@T?X@VAT?RAPAZAVCTEZCNGZG\\\\I^GPCJUl@Wn@Q`@OZOVa@v@{AnCYh@[j@Yf@]n@mA|Bk@fAkAvBS`@o@jA]n@QZ]n@_@p@KNs@lAMRKP]h@w@pAk@v@[`@Y^_BtBST_@`@i@h@g@d@g@b@URUPUPo@h@gA|@gA|@k@d@eBvAEBi@b@i@b@UPWTe@`@YVSRQNY\\\\QTY^UZW^g@x@]h@GHq@dASXW`@[`@S\\\\e@p@aA|AiBpCeA`Ba@p@OXu@zAk@nAO\\\\Wr@Wp@c@rASr@K^_@tA[rAi@tB[nAiAnEw@~CQp@e@lBCDg@pBg@pB[nAg@pBa@tAq@vBi@|Ai@zAc@nAc@nAc@pAq@pBa@lAm@jBaA|Ca@pAABCHCJIViApDk@|A_A~Bg@lAc@fAi@bBm@nBa@|AYdAKb@Mj@ETu@vDa@zB]~Au@~Dq@rDS|@Y|ACHAJMn@I`@I`@If@YjBm@bE?@Kr@?Dc@`Dg@jDYpB[pBc@fDMjAShBO~AQtAIj@Mx@[hBId@Ot@c@hBKf@Wz@c@lAKZYp@k@pAYr@k@jAMZeAbCuAhD}@dC}@`Cm@dBQd@Up@wB~FSh@g@rAEHSb@_@x@?Ba@t@KRSb@Wd@CD]n@c@x@o@bAiAjB_@l@ILILABMTKPMPqBfD{AbCu@hAOVmAjB[b@g@l@g@n@i@f@k@j@k@d@e@^m@b@_@V_@VC@ABSPQHUL{Av@iExB_@RyAt@u@`@k@XUN[P]To@d@URc@`@g@h@WVe@l@QTOVMR_@l@q@lAAB[l@q@lA_AdB_@r@}@`Bc@v@_A|As@hAKNMPg@n@c@h@QPEF_@`@_@^e@b@YTq@j@YPaAt@eAj@IFq@\\\\kB|@gKfFa@Pe@Re@Vs@\\\\s@b@[R_@X[Vg@f@[Zc@h@UXQRU\\\\g@~@i@dA]|@Y|@]tASbAMx@E`@E\\\\Ix@Cn@E~@?^Ah@AvAAv@?d@A|@Ar@C`@Ab@I~@ALCPE^G\\\\CTGZGZK^Kb@Oj@k@zAO^Yl@Wh@]l@u@vAu@vAOZ[h@]p@Uf@s@vAqDnHu@zAyBhEQXkA`Cg@|@Ud@]l@KN]j@Y\\\\iAvAKLQNCD]XMJg@`@[Re@Zu@`@q@XQJQFQF[JQDSFu@PaAT_@Js@Ne@LiDv@m@T?@oDr@iB^sATw@NQBKBiANaB^gAV}Bz@kCjAqBjAqBlAqC~BkAjAgBlBYXCFEDEFCB?@KLGFKJq@x@a@h@i@n@QXSVSVcAlAoA|AUXWZc@f@g@f@ONg@b@YTk@^_Al@eB`AWL[RYRWPQNQLa@\\\\ONQPi@h@iBjBEFMJWVMNo@n@uBvBo@p@\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 34.0256221,\n" +
            "                        \"lng\" : -118.2059515\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.1 mi\",\n" +
            "                        \"value\" : 180\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 11\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 34.1307998,\n" +
            "                        \"lng\" : -118.3485738\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Take exit \\u003cb\\u003e11B\\u003c/b\\u003e toward \\u003cb\\u003eUniversal Studios Blvd\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"ramp-right\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"e|hoEfwiqUAACAA?A?C?E?A@C@MHURYXKHa@^WV[VKJMLIFIFKHCBA?A?A@A?A?C?CA\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 34.1294658,\n" +
            "                        \"lng\" : -118.3475583\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.6 mi\",\n" +
            "                        \"value\" : 976\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"2 mins\",\n" +
            "                        \"value\" : 103\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 34.13502,\n" +
            "                        \"lng\" : -118.3570364\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Merge onto \\u003cb\\u003eW.C. Fields Dr\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"merge\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"odioEp}iqUMNQJYR_@Re@ZiBjASJk@^EBKJIHMLKLUTU\\\\Q\\\\Ub@GJKPGJGHCFEDCBCBCBC@EDEBEBC@EDCBEBEBCDCBC@CBEFCBCDEDEFINUd@ATCHEFGTM\\\\IVCJAL?N@JDTFZD^B^Bj@AD?PAXKt@_@hCQlAGVQ|@WfAc@nBw@jD\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 34.1307998,\n" +
            "                        \"lng\" : -118.3485738\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.2 mi\",\n" +
            "                        \"value\" : 313\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 66\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 34.1373841,\n" +
            "                        \"lng\" : -118.356905\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e onto \\u003cb\\u003eHotel Dr\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"turn-right\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"{~ioEnrkqUSEKEKAMAI?I@I?IBKBQDQDQBUFOB_@HQDE@GBKBMFIFGDIHCBIHA@A@A@C@C@C?C?E?w@CC?CACCAAACACACAEGYAC?CCQEOCKCICK\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 34.13502,\n" +
            "                        \"lng\" : -118.3570364\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"341 ft\",\n" +
            "                        \"value\" : 104\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 38\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 34.1364887,\n" +
            "                        \"lng\" : -118.3569926\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e onto \\u003cb\\u003eCoral Dr\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003eDestination will be on the left\\u003c/div\\u003e\",\n" +
            "                     \"maneuver\" : \"turn-right\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"smjoErqkqU^C@?@?@?@?@?@?@A@??A@A?Cx@VJBRBL@B?F?DAB?@?\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 34.1373841,\n" +
            "                        \"lng\" : -118.356905\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  }\n" +
            "               ],\n" +
            "               \"traffic_speed_entry\" : [],\n" +
            "               \"via_waypoint\" : []\n" +
            "            }\n" +
            "         ],\n" +
            "         \"overview_polyline\" : {\n" +
            "            \"points\" : \"kvkmElvvnU|@Tx@v@\\\\`ABjAF|Zn@?Cc\\\\q@{Aw@}@y@c@kBg@aD]}AD}@JeH`B{Bp@yAd@aAQyDmA_AW_BC_@FuAh@gJ~HeCxB}@nAeCtBgEhEoIbJgPjQk@h@aDnF_B~D{@|Cw@hDcAlHwB|NyAvFaMfYe`@h|@cGjK_BtCuCxGsCtHuH~QeFpMuU|i@yFfJgFbHmHlKeBdEcCxKgGdXoArDoC~GgD`H{HxMiF|K}IjSkE~JoLpWm@pAuIxRaDpG}A`CsF|GeGpI_KtTgLxWqXpn@iK|ReEfKuEhJqBvEeR|d@yGjPaDpHkHjOqR``@_NrYoDzIqDtHoBpD_HpKoH~ImKhKmKlKuE|EmEjGoDlHaDdHmCpFyAzBkG|IgGdGkCtBsRdMwQbLie@nYqPdLaQ|K{D`C{TrMqHfEia@xUa[vQkFlEgD~D{OhVyNzTeZrd@uBnCgCbCcCdBiE|BaFzAoXxFeFfAoEpAeFfCmAp@kQ|L{NpNyVxViCpCyCnEoD~FsAtB{AbBuArA{@x@cg@xe@wIhIeEpCwE~B}CpBwLxKqJ~IsCvCcPxRqC~EwCjFiE~EaGhFwBvBeC~DqEvL}IfW}AdFeCbM}CbHiA~BwA|DqJ~X_BtEaAvDwAtKMpDA`O@xWKxN]lE{@vFgAbEqGvR{O~e@yAnF@h@iAvEeGxQ_Lb\\cC|GsBxEmC|EcDhEqFzE{BrAuAl@kDt@}CPsGg@aCGuHXcCM_Dg@wHs@iACcLDiGZ_Fv@qFbBoEjBkCfAgElBkBnBaAvBg@tBQhDiB`R_BxUg@tHGjEJ|BIvDYdBk@jB}FbL_JrPgEjHyCjE}DvEqIfH_IrGyAxAyApB{EnHwGbKsCvFcBvE{A|FkHlYmChKiD`KoH`U{A~EwDrJsCvJuA|G{E`W_C`OgEt[y@zGyAnHsAlEkCbGgFnMsC~HyDfKuAvCwF|JoEnHoFpIuChDqFjEqOfI{BpAqCdC_BrBaBrCcKvQ}DvEkCvBcEfC{PjIiDlB_CtBaBtBqAdCw@zBeAtFYfEExEGxDQbCiAzFmBrEyCxFwNjYgDrGmCpDwDzCkCnAsA`@kEbA}F|AoRpDeErA}FvCqBlAqC~BsDxDi@n@WZgGvHaDzDyBpBiIfF}FvFkEnEw@l@M@aAx@oCbCYNqFhDkCvBeBzCg@`@e@^}@xAq@lCZ~Dm@jFcAjF{AzG_@Km@A{Bb@kBn@e@`@eACMGUeAQq@d@CHCfATl@DJA\"\n" +
            "         },\n" +
            "         \"summary\" : \"I-5 N and US-101 N\",\n" +
            "         \"warnings\" : [],\n" +
            "         \"waypoint_order\" : []\n" +
            "      }\n" +
            "   ],\n" +
            "   \"status\" : \"OK\"\n" +
            "}"

    @Test
    fun `test parseJson success`() {

        val result = parseJson(fakeGoogleRouteResp)
        val fakeGeocoderStatus = "OK"
        assert(result!!.geocodeMembers!![0]?.geocoderStatus.equals(fakeGeocoderStatus))
        val fakePlaceId: String = "ChIJa147K9HX3IAR-lwiGIQv9i4"
        assert(result!!.geocodeMembers!![0].placeId.equals(fakePlaceId))
        val faketypes = "amusement_park"
        assert(result!!.geocodeMembers!![0]?.types!![0].equals(faketypes))


        val fakeBoundNorthEast: Coordinates =
            models.googleMaps.Coordinates(latitude = 34.1373841, longitude = -117.9220826)
        val fakeBoundSouthEast: Coordinates =
            models.googleMaps.Coordinates(latitude = 33.8151707, longitude = -118.3575556)
        val fakeBound: Bounds = models.googleMaps.Bounds(northEast = fakeBoundNorthEast, southWest =  fakeBoundSouthEast)
        val fakeRouteCopyrights: String? = "Map data ©2019 Google"
        val fakeLegDistance: Distance =  models.googleMaps.Distance(text = "36.0 mi", value = 57961)
        val fakeLegDuration: Duration =  models.googleMaps.Duration(text = "50 mins", value = 3025)
        val fakeLegEndAddress: String = "100 Universal City Plaza, Universal City, CA 91608, USA"
        val fakeLegEndLocation: Coordinates =
            models.googleMaps.Coordinates(latitude = 34.1364887, longitude = -118.3569926)
        val fakeLegStartAddress: String = "1313 Disneyland Dr, Anaheim, CA 92802, USA"
        val fakeLegStartLocation: Coordinates =
            models.googleMaps.Coordinates(latitude = 33.8162219, longitude = -117.9224731)

        val fakeStepDistance: Distance = models.googleMaps.Distance(text = "59 ft", value = 18)
        val fakeStepDuration: Duration = models.googleMaps.Duration(text = "1 min", value = 2)
        val fakeStepEndLocation: Coordinates =
            models.googleMaps.Coordinates(latitude = 33.8160679, longitude = -117.9225314)
        val fakeStepHTMLInst: String = "Head <b>south</b>"
        val fakeStepPolyline: Polyline = models.googleMaps.Polyline(points = "kvkmElvvnU\\J")
        val fakeStepStartLocation: Coordinates =
            models.googleMaps.Coordinates(latitude = 33.8162219, longitude = -117.9224731)
        val fakeStepTravelMode: String = "DRIVING"

        val fakeLegStep: Step =  models.googleMaps.Step(distance = fakeStepDistance,
                                                      duration = fakeStepDuration,
                                                      endLocation = fakeStepEndLocation,
            htmlInstructions = fakeStepHTMLInst,
                                                      polyline = fakeStepPolyline,
                                                      startLocation = fakeStepStartLocation,
                                                      travelMode = fakeStepTravelMode)
        val fakeStepList: List<Step> = listOf(fakeLegStep)
        //yet to check what is coming in the response
        //val fakeTrafficSpeedHandle: List<Int?>? = null
        //val fakeViaWaypoints: List<Int?>? = null



        val fakeLegElement: Leg = models.googleMaps.Leg(distance = fakeLegDistance,
                                                        duration = fakeLegDuration,
                                                        endAddress = fakeLegEndAddress,
                                                        startAddress = fakeLegStartAddress,
                                                        endLocation = fakeLegEndLocation,
                                                        startLocation = fakeLegStartLocation,
                                                        steps = fakeStepList
                                                        //,trafficSpeedHandle = fakeTrafficSpeedHandle
                                                        //, viaWayPoints = fakeViaWaypoints)
        )
        val fakeListLeg: List<Leg> = listOf(fakeLegElement)

        val fakeRouteOverviewPolyline: Polyline = models.googleMaps.Polyline(points = "kvkmElvvnU|@Tx@v@\\`ABjAF|Zn@?Cc\\q@{Aw@}@y@c@kBg@aD]}AD}@JeH`B{Bp@yAd@aAQyDmA_AW_BC_@FuAh@gJ~HeCxB}@nAeCtBgEhEoIbJgPjQk@h@aDnF_B~D{@|Cw@hDcAlHwB|NyAvFaMfYe`@h|@cGjK_BtCuCxGsCtHuH~QeFpMuU|i@yFfJgFbHmHlKeBdEcCxKgGdXoArDoC~GgD`H{HxMiF|K}IjSkE~JoLpWm@pAuIxRaDpG}A`CsF|GeGpI_KtTgLxWqXpn@iK|ReEfKuEhJqBvEeR|d@yGjPaDpHkHjOqR``@_NrYoDzIqDtHoBpD_HpKoH~ImKhKmKlKuE|EmEjGoDlHaDdHmCpFyAzBkG|IgGdGkCtBsRdMwQbLie@nYqPdLaQ|K{D`C{TrMqHfEia@xUa[vQkFlEgD~D{OhVyNzTeZrd@uBnCgCbCcCdBiE|BaFzAoXxFeFfAoEpAeFfCmAp@kQ|L{NpNyVxViCpCyCnEoD~FsAtB{AbBuArA{@x@cg@xe@wIhIeEpCwE~B}CpBwLxKqJ~IsCvCcPxRqC~EwCjFiE~EaGhFwBvBeC~DqEvL}IfW}AdFeCbM}CbHiA~BwA|DqJ~X_BtEaAvDwAtKMpDA`O@xWKxN]lE{@vFgAbEqGvR{O~e@yAnF@h@iAvEeGxQ_LbcC|GsBxEmC|EcDhEqFzE{BrAuAl@kDt@}CPsGg@aCGuHXcCM_Dg@wHs@iACcLDiGZ_Fv@qFbBoEjBkCfAgElBkBnBaAvBg@tBQhDiB`R_BxUg@tHGjEJ|BIvDYdBk@jB}FbL_JrPgEjHyCjE}DvEqIfH_IrGyAxAyApB{EnHwGbKsCvFcBvE{A|FkHlYmChKiD`KoH`U{A~EwDrJsCvJuA|G{E`W_C`OgEt[y@zGyAnHsAlEkCbGgFnMsC~HyDfKuAvCwF|JoEnHoFpIuChDqFjEqOfI{BpAqCdC_BrBaBrCcKvQ}DvEkCvBcEfC{PjIiDlB_CtBaBtBqAdCw@zBeAtFYfEExEGxDQbCiAzFmBrEyCxFwNjYgDrGmCpDwDzCkCnAsA`@kEbA}F|AoRpDeErA}FvCqBlAqC~BsDxDi@n@WZgGvHaDzDyBpBiIfF}FvFkEnEw@l@M@aAx@oCbCYNqFhDkCvBeBzCg@`@e@^}@xAq@lCZ~Dm@jFcAjF{AzG_@Km@A{Bb@kBn@e@`@eACMGUeAQq@d@CHCfATl@DJA\n" +
                "\n")
        val fakeRouteSummary: String = "I-5 N and US-101 N"
        //Still to check the response in the Google response
        //val fakeRouteWayPointOrder: List<Int> = null
        //val fakeRouteWarnings: List<String> = null
        val fakeRoute = models.googleMaps.GoogleRouteModel(
            bounds = fakeBound,
                                                         copyrights = fakeRouteCopyrights,
                                                         legs = fakeListLeg,
                                                         overviewPolyline = fakeRouteOverviewPolyline,
                                                         summary = fakeRouteSummary
                                                         //warning = fakeRouteWarning,
                                                        // wayPointOrder = fakeRouteWayPointOrder
                                                         )
        val fakeListRoute: List<GoogleRouteModel> = listOf(fakeRoute)
        assert(result!!.routes!![0]?.bounds?.northEast?.latitude == fakeBound.northEast?.latitude)
        assert(result!!.routes!![0]?.bounds?.northEast?.longitude == fakeBound.northEast?.longitude)
        assert(result!!.routes!![0]?.bounds?.southWest?.latitude == fakeBound.southWest?.latitude)
        assert(result!!.routes!![0]?.bounds?.southWest?.longitude == fakeBound.southWest?.longitude)
        assert(result!!.routes!![0].copyrights == fakeRouteCopyrights)
        assert(result!!.routes!![0].legs!![0].distance?.text == fakeLegDistance.text )
        assert(result!!.routes!![0].legs!![0].distance?.value == fakeLegDistance.value)
        assert(result!!.routes!![0].legs!![0].duration?.text == fakeLegDuration.text)
        assert(result!!.routes!![0].legs!![0].duration?.value == fakeLegDuration.value)
        assert(result!!.routes!![0].legs!![0].endAddress == fakeLegEndAddress)
        assert(result!!.routes!![0].legs!![0].startAddress == fakeLegStartAddress)
        assert(result!!.routes!![0].legs!![0].startLocation?.latitude == fakeLegStartLocation.latitude)
        assert(result!!.routes!![0].legs!![0].startLocation?.longitude == fakeLegStartLocation.longitude)
        assert(result!!.routes!![0].legs!![0].endLocation?.latitude == fakeLegEndLocation.latitude)
        assert(result!!.routes!![0].legs!![0].endLocation?.longitude == fakeLegEndLocation.longitude)
        assert(result!!.routes!![0].legs!![0].steps!![0].distance?.text == fakeStepDistance.text)
        assert(result!!.routes!![0].legs!![0].steps!![0].distance?.value == fakeStepDistance.value)
        assert(result!!.routes!![0].legs!![0].steps!![0].duration?.text == fakeStepDuration.text)
        assert(result!!.routes!![0].legs!![0].steps!![0].duration?.value == fakeStepDuration.value)
        assert(result!!.routes!![0].legs!![0].steps!![0].endLocation?.latitude == fakeStepEndLocation.latitude)
        assert(result!!.routes!![0].legs!![0].steps!![0].endLocation?.longitude == fakeStepEndLocation.longitude)
        assert(result!!.routes!![0].legs!![0].steps!![0].startLocation?.latitude == fakeStepStartLocation.latitude)
        print(result!!.routes!![0].overviewPolyline?.points)
        print("\n")
        print(fakeRouteOverviewPolyline.points)
        assert(result!!.routes!![0].legs!![0].steps!![0].startLocation?.longitude == fakeStepStartLocation.longitude)
        assert(result!!.routes!![0].legs!![0].steps!![0].htmlInstructions == fakeStepHTMLInst)
        assert(result!!.routes!![0].legs!![0].steps!![0].polyline?.points == fakeStepPolyline.points)
        assert(result!!.routes!![0].legs!![0].steps!![0].travelMode == fakeStepTravelMode)
        //Yet to recieve in the resposne
        //assert(result!!.routes!![0].legs!![0].trafficSpeedEntry!![0] == fakeLegtrafficSpeedEntry[0])
        //assert(result!!.routes!![0].legs!![0].viaWayPoint!![0] == fakeLegViaWayPoint[0])
        //assert(result!!.routes!![0].overviewPolyline?.points == fakeRouteOverviewPolyline.points)
        assert(result!!.routes!![0].summary == fakeRouteSummary)
        //Yet to recieve in the respose
        //assert(result!!.routes!![0].warnings = fakeRouteWarnings[0])
        //assert(result!!.routes!![0].wayPointOrder  = fakeRouteWayPointOrder[0])

        val fakeStatus: String = "OK"
        assert(result!!.status!!.equals(fakeStatus))



    }

}