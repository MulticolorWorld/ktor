/*
 * Copyright 2014-2019 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.http

import io.ktor.util.*
import kotlin.native.concurrent.*

private val rawMimes: String
    get() = """
.123,application/vnd.lotus-1-2-3
.3dmf,x-world/x-3dmf
.3dml,text/vnd.in3d.3dml
.3dm,x-world/x-3dmf
.3g2,video/3gpp2
.3gp,video/3gpp
.7z,application/x-7z-compressed
.aab,application/x-authorware-bin
.aac,audio/aac
.aam,application/x-authorware-map
.a,application/octet-stream
.aas,application/x-authorware-seg
.abc,text/vnd.abc
.abw,application/x-abiword
.ac,application/pkix-attr-cert
.acc,application/vnd.americandynamics.acc
.ace,application/x-ace-compressed
.acgi,text/html
.acu,application/vnd.acucobol
.adp,audio/adpcm
.aep,application/vnd.audiograph
.afl,video/animaflex
.afp,application/vnd.ibm.modcap
.ahead,application/vnd.ahead.space
.ai,application/postscript
.aif,audio/aiff
.aifc,audio/aiff
.aiff,audio/aiff
.aim,application/x-aim
.aip,text/x-audiosoft-intra
.air,application/vnd.adobe.air-application-installer-package+zip
.ait,application/vnd.dvb.ait
.ami,application/vnd.amiga.ami
.ani,application/x-navi-animation
.aos,application/x-nokia-9000-communicator-add-on-software
.apk,application/vnd.android.package-archive
.application,application/x-ms-application
,application/pgp-encrypted
.apr,application/vnd.lotus-approach
.aps,application/mime
.arc,application/octet-stream
.arj,application/arj
.arj,application/octet-stream
.art,image/x-jg
.asf,video/x-ms-asf
.asm,text/x-asm
.aso,application/vnd.accpac.simply.aso
.asp,text/asp
.asx,application/x-mplayer2
.asx,video/x-ms-asf
.asx,video/x-ms-asf-plugin
.atc,application/vnd.acucorp
.atomcat,application/atomcat+xml
.atomsvc,application/atomsvc+xml
.atom,application/atom+xml
.atx,application/vnd.antix.game-component
.au,audio/basic
.au,audio/x-au
.avi,video/avi
.avi,video/msvideo
.avi,video/x-msvideo
.avs,video/avs-video
.aw,application/applixware
.azf,application/vnd.airzip.filesecure.azf
.azs,application/vnd.airzip.filesecure.azs
.azw,application/vnd.amazon.ebook
.bcpio,application/x-bcpio
.bdf,application/x-font-bdf
.bdm,application/vnd.syncml.dm+wbxml
.bed,application/vnd.realvnc.bed
.bh2,application/vnd.fujitsu.oasysprs
.bin,application/macbinary
.bin,application/mac-binary
.bin,application/octet-stream
.bin,application/x-binary
.bin,application/x-macbinary
.bmi,application/vnd.bmi
.bm,image/bmp
.bmp,image/bmp
.bmp,image/x-windows-bmp
.boo,application/book
.book,application/book
.box,application/vnd.previewsystems.box
.boz,application/x-bzip2
.bsh,application/x-bsh
.btif,image/prs.btif
.bz2,application/x-bzip2
.bz,application/x-bzip
.c11amc,application/vnd.cluetrust.cartomobile-config
.c11amz,application/vnd.cluetrust.cartomobile-config-pkg
.c4g,application/vnd.clonk.c4group
.cab,application/vnd.ms-cab-compressed
.car,application/vnd.curl.car
.cat,application/vnd.ms-pki.seccat
.ccad,application/clariscad
.cco,application/x-cocoa
.cc,text/plain
.cc,text/x-c
.ccxml,application/ccxml+xml,
.cdbcmsg,application/vnd.contact.cmsg
.cdf,application/cdf
.cdf,application/x-cdf
.cdf,application/x-netcdf
.cdkey,application/vnd.mediastation.cdkey
.cdmia,application/cdmi-capability
.cdmic,application/cdmi-container
.cdmid,application/cdmi-domain
.cdmio,application/cdmi-object
.cdmiq,application/cdmi-queue
.cdx,chemical/x-cdx
.cdxml,application/vnd.chemdraw+xml
.cdy,application/vnd.cinderella
.cer,application/pkix-cert
.cgm,image/cgm
.cha,application/x-chat
.chat,application/x-chat
.chm,application/vnd.ms-htmlhelp
.chrt,application/vnd.kde.kchart
.cif,chemical/x-cif
.cii,application/vnd.anser-web-certificate-issue-initiation
.cil,application/vnd.ms-artgalry
.cla,application/vnd.claymore
.class,application/java
.class,application/java-byte-code
.class,application/java-vm
.class,application/x-java-class
.clkk,application/vnd.crick.clicker.keyboard
.clkp,application/vnd.crick.clicker.palette
.clkt,application/vnd.crick.clicker.template
.clkw,application/vnd.crick.clicker.wordbank
.clkx,application/vnd.crick.clicker
.clp,application/x-msclip
.cmc,application/vnd.cosmocaller
.cmdf,chemical/x-cmdf
.cml,chemical/x-cml
.cmp,application/vnd.yellowriver-custom-menu
.cmx,image/x-cmx
.cod,application/vnd.rim.cod
.com,application/octet-stream
.com,text/plain
.conf,text/plain
.cpio,application/x-cpio
.cpp,text/x-c
.cpt,application/mac-compactpro
.cpt,application/x-compactpro
.cpt,application/x-cpt
.crd,application/x-mscardfile
.crl,application/pkcs-crl
.crl,application/pkix-crl
.crt,application/pkix-cert
.crt,application/x-x509-ca-cert
.crt,application/x-x509-user-cert
.cryptonote,application/vnd.rig.cryptonote
.csh,application/x-csh
.csh,text/x-script.csh
.csml,chemical/x-csml
.csp,application/vnd.commonspace
.css,text/css
.csv,text/csv
.c,text/plain
.c++,text/plain
.c,text/x-c
.cu,application/cu-seeme
.curl,text/vnd.curl
.cww,application/prs.cww
.cxx,text/plain
.dat,binary/octet-stream
.dae,model/vnd.collada+xml
.daf,application/vnd.mobius.daf
.davmount,application/davmount+xml
.dcr,application/x-director
.dcurl,text/vnd.curl.dcurl
.dd2,application/vnd.oma.dd2+xml
.ddd,application/vnd.fujixerox.ddd
.deb,application/x-debian-package
.deepv,application/x-deepv
.def,text/plain
.der,application/x-x509-ca-cert
.dfac,application/vnd.dreamfactory
.dif,video/x-dv
.dir,application/x-director
.dis,application/vnd.mobius.dis
.djvu,image/vnd.djvu
.dl,video/dl
.dl,video/x-dl
.dna,application/vnd.dna
.doc,application/msword
.docm,application/vnd.ms-word.document.macroenabled.12
.docx,application/vnd.openxmlformats-officedocument.wordprocessingml.document
.dot,application/msword
.dotm,application/vnd.ms-word.template.macroenabled.12
.dotx,application/vnd.openxmlformats-officedocument.wordprocessingml.template
.dp,application/commonground
.dp,application/vnd.osgi.dp
.dpg,application/vnd.dpgraph
.dra,audio/vnd.dra
.drw,application/drafting
.dsc,text/prs.lines.tag
.dssc,application/dssc+der
.dtb,application/x-dtbook+xml
.dtd,application/xml-dtd
.dts,audio/vnd.dts
.dtshd,audio/vnd.dts.hd
.dump,application/octet-stream
.dvi,application/x-dvi
.dv,video/x-dv
.dwf,drawing/x-dwf (old)
.dwf,model/vnd.dwf
.dwg,application/acad
.dwg,image/vnd.dwg
.dwg,image/x-dwg
.dxf,application/dxf
.dxf,image/vnd.dwg
.dxf,image/vnd.dxf
.dxf,image/x-dwg
.dxp,application/vnd.spotfire.dxp
.dxr,application/x-director
.ecelp4800,audio/vnd.nuera.ecelp4800
.ecelp7470,audio/vnd.nuera.ecelp7470
.ecelp9600,audio/vnd.nuera.ecelp9600
.edm,application/vnd.novadigm.edm
.edx,application/vnd.novadigm.edx
.efif,application/vnd.picsel
.ei6,application/vnd.pg.osasli
.elc,application/x-bytecode.elisp (compiled elisp)
.elc,application/x-elc
.el,text/x-script.elisp
.eml,message/rfc822
.emma,application/emma+xml
.env,application/x-envoy
.eol,audio/vnd.digital-winds
.eot,application/vnd.ms-fontobject
.eps,application/postscript
.epub,application/epub+zip
.es3,application/vnd.eszigno3+xml
.es,application/ecmascript
.es,application/x-esrehber
.esf,application/vnd.epson.esf
.etx,text/x-setext
.evy,application/envoy
.evy,application/x-envoy
.exe,application/octet-stream
.exe,application/x-msdownload
.exi,application/exi
.ext,application/vnd.novadigm.ext
.ez2,application/vnd.ezpix-album
.ez3,application/vnd.ezpix-package
.f4v,video/x-f4v
.f77,text/x-fortran
.f90,text/plain
.f90,text/x-fortran
.fbs,image/vnd.fastbidsheet
.fcs,application/vnd.isac.fcs
.fdf,application/vnd.fdf
.fe_launch,application/vnd.denovo.fcselayout-link
.fg5,application/vnd.fujitsu.oasysgp
.fh,image/x-freehand
.fif,application/fractals
.fif,image/fif
.fig,application/x-xfig
.fli,video/fli
.fli,video/x-fli
.flo,application/vnd.micrografx.flo
.flo,image/florian
.flv,video/x-flv
.flw,application/vnd.kde.kivio
.flx,text/vnd.fmi.flexstor
.fly,text/vnd.fly
.fm,application/vnd.framemaker
.fmf,video/x-atomic3d-feature
.fnc,application/vnd.frogans.fnc
.for,text/plain
.for,text/x-fortran
.fpx,image/vnd.fpx
.fpx,image/vnd.net-fpx
.frl,application/freeloader
.fsc,application/vnd.fsc.weblaunch
.fst,image/vnd.fst
.ftc,application/vnd.fluxtime.clip
.f,text/plain
.f,text/x-fortran
.fti,application/vnd.anser-web-funds-transfer-initiation
.funk,audio/make
.fvt,video/vnd.fvt
.fxp,application/vnd.adobe.fxp
.fzs,application/vnd.fuzzysheet
.g2w,application/vnd.geoplan
.g3,image/g3fax
.g3w,application/vnd.geospace
.gac,application/vnd.groove-account
.gdl,model/vnd.gdl
.geo,application/vnd.dynageo
.gex,application/vnd.geometry-explorer
.ggb,application/vnd.geogebra.file
.ggt,application/vnd.geogebra.tool
.ghf,application/vnd.groove-help
.gif,image/gif
.gim,application/vnd.groove-identity-message
.gl,video/gl
.gl,video/x-gl
.gmx,application/vnd.gmx
.gnumeric,application/x-gnumeric
.gph,application/vnd.flographit
.gqf,application/vnd.grafeq
.gram,application/srgs
.grv,application/vnd.groove-injector
.grxml,application/srgs+xml
.gsd,audio/x-gsm
.gsf,application/x-font-ghostscript
.gsm,audio/x-gsm
.gsp,application/x-gsp
.gss,application/x-gss
.gtar,application/x-gtar
.g,text/plain
.gtm,application/vnd.groove-tool-message
.gtw,model/vnd.gtw
.gv,text/vnd.graphviz
.gxt,application/vnd.geonext
.gz,application/x-compressed
.gz,application/x-gzip
.gzip,application/x-gzip
.gzip,multipart/x-gzip
.h261,video/h261
.h263,video/h263
.h264,video/h264
.hal,application/vnd.hal+xml
.hbci,application/vnd.hbci
.hdf,application/x-hdf
.help,application/x-helpfile
.hgl,application/vnd.hp-hpgl
.hh,text/plain
.hh,text/x-h
.hlb,text/x-script
.hlp,application/hlp
.hlp,application/winhlp
.hlp,application/x-helpfile
.hlp,application/x-winhelp
.hpg,application/vnd.hp-hpgl
.hpgl,application/vnd.hp-hpgl
.hpid,application/vnd.hp-hpid
.hps,application/vnd.hp-hps
.hqx,application/binhex
.hqx,application/binhex4
.hqx,application/mac-binhex
.hqx,application/mac-binhex40
.hqx,application/x-binhex40
.hqx,application/x-mac-binhex40
.hta,application/hta
.htc,text/x-component
.h,text/plain
.h,text/x-h
.htke,application/vnd.kenameaapp
.htmls,text/html
.html,text/html
.htm,text/html
.htt,text/webviewhtml
.htx,text/html
.hvd,application/vnd.yamaha.hv-dic
.hvp,application/vnd.yamaha.hv-voice
.hvs,application/vnd.yamaha.hv-script
.i2g,application/vnd.intergeo
.icc,application/vnd.iccprofile
.ice,x-conference/x-cooltalk
.ico,image/x-icon
.ics,text/calendar
.idc,text/plain
.ief,image/ief
.iefs,image/ief
.iff,application/iff
.ifm,application/vnd.shana.informed.formdata
.iges,application/iges
.iges,model/iges
.igl,application/vnd.igloader
.igm,application/vnd.insors.igm
.igs,application/iges
.igs,model/iges
.igx,application/vnd.micrografx.igx
.iif,application/vnd.shana.informed.interchange
.ima,application/x-ima
.imap,application/x-httpd-imap
.imp,application/vnd.accpac.simply.imp
.ims,application/vnd.ms-ims
.inf,application/inf
.ins,application/x-internett-signup
.ip,application/x-ip2
.ipfix,application/ipfix
.ipk,application/vnd.shana.informed.package
.irm,application/vnd.ibm.rights-management
.irp,application/vnd.irepository.package+xml
.isu,video/x-isvideo
.it,audio/it
.itp,application/vnd.shana.informed.formtemplate
.iv,application/x-inventor
.ivp,application/vnd.immervision-ivp
.ivr,i-world/i-vrml
.ivu,application/vnd.immervision-ivu
.ivy,application/x-livescreen
.jad,text/vnd.sun.j2me.app-descriptor
.jam,application/vnd.jam
.jam,audio/x-jam
.jar,application/java-archive
.java,text/plain
.java,text/x-java-source
.jav,text/plain
.jav,text/x-java-source
.jcm,application/x-java-commerce
.jfif,image/jpeg
.jfif,image/pjpeg
.jfif-tbnl,image/jpeg
.jisp,application/vnd.jisp
.jlt,application/vnd.hp-jlyt
.jnlp,application/x-java-jnlp-file
.joda,application/vnd.joost.joda-archive
.jpeg,image/jpeg
.jpe,image/jpeg
.jpg,image/jpeg
.jpgv,video/jpeg
.jpm,video/jpm
.jps,image/x-jps
.js,application/javascript
.json,application/json
.jut,image/jutvision
.kar,audio/midi
.karbon,application/vnd.kde.karbon
.kar,music/x-karaoke
.key,application/pgp-keys
.keychain,application/octet-stream
.kfo,application/vnd.kde.kformula
.kia,application/vnd.kidspiration
.kml,application/vnd.google-earth.kml+xml
.kmz,application/vnd.google-earth.kmz
.kne,application/vnd.kinar
.kon,application/vnd.kde.kontour
.kpr,application/vnd.kde.kpresenter
.ksh,application/x-ksh
.ksh,text/x-script.ksh
.ksp,application/vnd.kde.kspread
.ktx,image/ktx
.ktz,application/vnd.kahootz
.kwd,application/vnd.kde.kword
.la,audio/nspaudio
.la,audio/x-nspaudio
.lam,audio/x-liveaudio
.lasxml,application/vnd.las.las+xml
.latex,application/x-latex
.lbd,application/vnd.llamagraphics.life-balance.desktop
.lbe,application/vnd.llamagraphics.life-balance.exchange+xml
.les,application/vnd.hhe.lesson-player
.lha,application/lha
.lha,application/x-lha
.link66,application/vnd.route66.link66+xml
.list,text/plain
.lma,audio/nspaudio
.lma,audio/x-nspaudio
.log,text/plain
.lrm,application/vnd.ms-lrm
.lsp,application/x-lisp
.lsp,text/x-script.lisp
.lst,text/plain
.lsx,text/x-la-asf
.ltf,application/vnd.frogans.ltf
.ltx,application/x-latex
.lvp,audio/vnd.lucent.voice
.lwp,application/vnd.lotus-wordpro
.lzh,application/octet-stream
.lzh,application/x-lzh
.lzx,application/lzx
.lzx,application/octet-stream
.lzx,application/x-lzx
.m1v,video/mpeg
.m21,application/mp21
.m2a,audio/mpeg
.m2v,video/mpeg
.m3u8,application/vnd.apple.mpegurl
.m3u,audio/x-mpegurl
.m4a,audio/mp4
.m4v,video/mp4
.ma,application/mathematica
.mads,application/mads+xml
.mag,application/vnd.ecowin.chart
.man,application/x-troff-man
.map,application/x-navimap
.mar,text/plain
.mathml,application/mathml+xml
.mbd,application/mbedlet
.mbk,application/vnd.mobius.mbk
.mbox,application/mbox
.mc1,application/vnd.medcalcdata
.mc${'$'},application/x-magic-cap-package-1.0
.mcd,application/mcad
.mcd,application/vnd.mcd
.mcd,application/x-mathcad
.mcf,image/vasa
.mcf,text/mcf
.mcp,application/netmc
.mcurl,text/vnd.curl.mcurl
.mdb,application/x-msaccess
.mdi,image/vnd.ms-modi
.me,application/x-troff-me
.meta4,application/metalink4+xml
.mets,application/mets+xml
.mfm,application/vnd.mfmp
.mgp,application/vnd.osgeo.mapguide.package
.mgz,application/vnd.proteus.magazine
.mht,message/rfc822
.mhtml,message/rfc822
.mid,application/x-midi
.mid,audio/midi
.mid,audio/x-mid
.midi,application/x-midi
.midi,audio/midi
.midi,audio/x-mid
.midi,audio/x-midi
.midi,music/crescendo
.midi,x-music/x-midi
.mid,music/crescendo
.mid,x-music/x-midi
.mif,application/vnd.mif
.mif,application/x-frame
.mif,application/x-mif
.mime,message/rfc822
.mime,www/mime
.mj2,video/mj2
.mjf,audio/x-vnd.audioexplosion.mjuicemediafile
.mjpg,video/x-motion-jpeg
.mkv,video/x-matroska
.mkv,audio/x-matroska
.mlp,application/vnd.dolby.mlp
.mm,application/base64
.mm,application/x-meme
.mmd,application/vnd.chipnuts.karaoke-mmd
.mme,application/base64
.mmf,application/vnd.smaf
.mmr,image/vnd.fujixerox.edmics-mmr
.mny,application/x-msmoney
.mod,audio/mod
.mod,audio/x-mod
.mods,application/mods+xml
.moov,video/quicktime
.movie,video/x-sgi-movie
.mov,video/quicktime
.mp2,audio/mpeg
.mp2,audio/x-mpeg
.mp2,video/mpeg
.mp2,video/x-mpeg
.mp2,video/x-mpeq2a
.mp3,audio/mpeg
.mp3,audio/mpeg3
.mp4a,audio/mp4
.mp4,application/mp4
.mp4,video/mp4
.mpa,audio/mpeg
.mpc,application/vnd.mophun.certificate
.mpc,application/x-project
.mpeg,video/mpeg
.mpe,video/mpeg
.mpga,audio/mpeg
.mpg,video/mpeg
.mpg,audio/mpeg
.mpkg,application/vnd.apple.installer+xml
.mpm,application/vnd.blueice.multipass
.mpn,application/vnd.mophun.application
.mpp,application/vnd.ms-project
.mpt,application/x-project
.mpv,application/x-project
.mpx,application/x-project
.mpy,application/vnd.ibm.minipay
.mqy,application/vnd.mobius.mqy
.mrc,application/marc
.mrcx,application/marcxml+xml
.ms,application/x-troff-ms
.mscml,application/mediaservercontrol+xml
.mseq,application/vnd.mseq
.msf,application/vnd.epson.msf
.msg,application/vnd.ms-outlook
.msh,model/mesh
.msl,application/vnd.mobius.msl
.msty,application/vnd.muvee.style
.m,text/plain
.m,text/x-m
.mts,model/vnd.mts
.mus,application/vnd.musician
.musicxml,application/vnd.recordare.musicxml+xml
.mvb,application/x-msmediaview
.mv,video/x-sgi-movie
.mwf,application/vnd.mfer
.mxf,application/mxf
.mxl,application/vnd.recordare.musicxml
.mxml,application/xv+xml
.mxs,application/vnd.triscape.mxs
.mxu,video/vnd.mpegurl
.my,audio/make
.mzz,application/x-vnd.audioexplosion.mzz
.n3,text/n3
N/A,application/andrew-inset
.nap,image/naplps
.naplps,image/naplps
.nbp,application/vnd.wolfram.player
.nc,application/x-netcdf
.ncm,application/vnd.nokia.configuration-message
.ncx,application/x-dtbncx+xml
.n-gage,application/vnd.nokia.n-gage.symbian.install
.ngdat,application/vnd.nokia.n-gage.data
.niff,image/x-niff
.nif,image/x-niff
.nix,application/x-mix-transfer
.nlu,application/vnd.neurolanguage.nlu
.nml,application/vnd.enliven
.nnd,application/vnd.noblenet-directory
.nns,application/vnd.noblenet-sealer
.nnw,application/vnd.noblenet-web
.npx,image/vnd.net-fpx
.nsc,application/x-conference
.nsf,application/vnd.lotus-notes
.nvd,application/x-navidoc
.oa2,application/vnd.fujitsu.oasys2
.oa3,application/vnd.fujitsu.oasys3
.o,application/octet-stream
.oas,application/vnd.fujitsu.oasys
.obd,application/x-msbinder
.oda,application/oda
.odb,application/vnd.oasis.opendocument.database
.odc,application/vnd.oasis.opendocument.chart
.odf,application/vnd.oasis.opendocument.formula
.odft,application/vnd.oasis.opendocument.formula-template
.odg,application/vnd.oasis.opendocument.graphics
.odi,application/vnd.oasis.opendocument.image
.odm,application/vnd.oasis.opendocument.text-master
.odp,application/vnd.oasis.opendocument.presentation
.ods,application/vnd.oasis.opendocument.spreadsheet
.odt,application/vnd.oasis.opendocument.text
.oga,audio/ogg
.ogg,audio/ogg
.ogv,video/ogg
.ogx,application/ogg
.omc,application/x-omc
.omcd,application/x-omcdatamaker
.omcr,application/x-omcregerator
.onetoc,application/onenote
.opf,application/oebps-package+xml
.org,application/vnd.lotus-organizer
.osf,application/vnd.yamaha.openscoreformat
.osfpvg,application/vnd.yamaha.openscoreformat.osfpvg+xml
.otc,application/vnd.oasis.opendocument.chart-template
.otf,application/x-font-otf
.otg,application/vnd.oasis.opendocument.graphics-template
.oth,application/vnd.oasis.opendocument.text-web
.oti,application/vnd.oasis.opendocument.image-template
.otp,application/vnd.oasis.opendocument.presentation-template
.ots,application/vnd.oasis.opendocument.spreadsheet-template
.ott,application/vnd.oasis.opendocument.text-template
.oxt,application/vnd.openofficeorg.extension
.p10,application/pkcs10
.p12,application/pkcs-12
.p7a,application/x-pkcs7-signature
.p7b,application/x-pkcs7-certificates
.p7c,application/pkcs7-mime
.p7m,application/pkcs7-mime
.p7r,application/x-pkcs7-certreqresp
.p7s,application/pkcs7-signature
.p8,application/pkcs8
.pages,application/vnd.apple.pages
.part,application/pro_eng
.par,text/plain-bas
.pas,text/pascal
.paw,application/vnd.pawaafile
.pbd,application/vnd.powerbuilder6
.pbm,image/x-portable-bitmap
.pcf,application/x-font-pcf
.pcl,application/vnd.hp-pcl
.pcl,application/x-pcl
.pclxl,application/vnd.hp-pclxl
.pct,image/x-pict
.pcurl,application/vnd.curl.pcurl
.pcx,image/x-pcx
.pdb,application/vnd.palm
.pdb,chemical/x-pdb
.pdf,application/pdf
.pem,application/x-pem-file
.pfa,application/x-font-type1
.pfr,application/font-tdpfr
.pfunk,audio/make
.pfunk,audio/make.my.funk
.pfx,application/x-pkcs12
.pgm,image/x-portable-graymap
.pgn,application/x-chess-pgn
.pgp,application/pgp-signature
.pic,image/pict
.pict,image/pict
.pkg,application/x-newton-compatible-pkg
.pki,application/pkixcmp
.pkipath,application/pkix-pkipath
.pko,application/vnd.ms-pki.pko
.plb,application/vnd.3gpp.pic-bw-large
.plc,application/vnd.mobius.plc
.plf,application/vnd.pocketlearn
.pls,application/pls+xml
.pl,text/plain
.pl,text/x-script.perl
.plx,application/x-pixclscript
.pm4,application/x-pagemaker
.pm5,application/x-pagemaker
.pm,image/x-xpixmap
.pml,application/vnd.ctc-posml
.pm,text/x-script.perl-module
.png,image/png
.pnm,application/x-portable-anymap
.pnm,image/x-portable-anymap
.portpkg,application/vnd.macports.portpkg
.pot,application/mspowerpoint
.pot,application/vnd.ms-powerpoint
.potm,application/vnd.ms-powerpoint.template.macroenabled.12
.potx,application/vnd.openxmlformats-officedocument.presentationml.template
.pov,model/x-pov
.ppa,application/vnd.ms-powerpoint
.ppam,application/vnd.ms-powerpoint.addin.macroenabled.12
.ppd,application/vnd.cups-ppd
.ppm,image/x-portable-pixmap
.pps,application/mspowerpoint
.pps,application/vnd.ms-powerpoint
.ppsm,application/vnd.ms-powerpoint.slideshow.macroenabled.12
.ppsx,application/vnd.openxmlformats-officedocument.presentationml.slideshow
.ppt,application/mspowerpoint
.ppt,application/powerpoint
.ppt,application/vnd.ms-powerpoint
.ppt,application/x-mspowerpoint
.pptm,application/vnd.ms-powerpoint.presentation.macroenabled.12
.pptx,application/vnd.openxmlformats-officedocument.presentationml.presentation
.ppz,application/mspowerpoint
.prc,application/x-mobipocket-ebook
.pre,application/vnd.lotus-freelance
.pre,application/x-freelance
.prf,application/pics-rules
.prt,application/pro_eng
.ps,application/postscript
.psb,application/vnd.3gpp.pic-bw-small
.psd,application/octet-stream
.psd,image/vnd.adobe.photoshop
.psf,application/x-font-linux-psf
.pskcxml,application/pskc+xml
.p,text/x-pascal
.ptid,application/vnd.pvi.ptid1
.pub,application/x-mspublisher
.pvb,application/vnd.3gpp.pic-bw-var
.pvu,paleovu/x-pv
.pwn,application/vnd.3m.post-it-notes
.pwz,application/vnd.ms-powerpoint
.pya,audio/vnd.ms-playready.media.pya
.pyc,applicaiton/x-bytecode.python
.py,text/x-script.phyton
.pyv,video/vnd.ms-playready.media.pyv
.qam,application/vnd.epson.quickanime
.qbo,application/vnd.intu.qbo
.qcp,audio/vnd.qcelp
.qd3d,x-world/x-3dmf
.qd3,x-world/x-3dmf
.qfx,application/vnd.intu.qfx
.qif,image/x-quicktime
.qps,application/vnd.publishare-delta-tree
.qtc,video/x-qtc
.qtif,image/x-quicktime
.qti,image/x-quicktime
.qt,video/quicktime
.qxd,application/vnd.quark.quarkxpress
.ra,audio/x-pn-realaudio
.ra,audio/x-pn-realaudio-plugin
.ra,audio/x-realaudio
.ram,audio/x-pn-realaudio
.rar,application/x-rar-compressed
.ras,application/x-cmu-raster
.ras,image/cmu-raster
.ras,image/x-cmu-raster
.rast,image/cmu-raster
.rcprofile,application/vnd.ipunplugged.rcprofile
.rdf,application/rdf+xml
.rdz,application/vnd.data-vision.rdz
.rep,application/vnd.businessobjects
.res,application/x-dtbresource+xml
.rexx,text/x-script.rexx
.rf,image/vnd.rn-realflash
.rgb,image/x-rgb
.rif,application/reginfo+xml
.rip,audio/vnd.rip
.rl,application/resource-lists+xml
.rlc,image/vnd.fujixerox.edmics-rlc
.rld,application/resource-lists-diff+xml
.rm,application/vnd.rn-realmedia
.rm,audio/x-pn-realaudio
.rmi,audio/mid
.rmm,audio/x-pn-realaudio
.rmp,audio/x-pn-realaudio
.rmp,audio/x-pn-realaudio-plugin
.rms,application/vnd.jcp.javame.midlet-rms
.rnc,application/relax-ng-compact-syntax
.rng,application/ringing-tones
.rng,application/vnd.nokia.ringing-tone
.rnx,application/vnd.rn-realplayer
.roff,application/x-troff
.rp9,application/vnd.cloanto.rp9
.rp,image/vnd.rn-realpix
.rpm,audio/x-pn-realaudio-plugin
.rpm,application/x-rpm
.rpss,application/vnd.nokia.radio-presets
.rpst,application/vnd.nokia.radio-preset
.rq,application/sparql-query
.rs,application/rls-services+xml
.rsd,application/rsd+xml
.rss,application/rss+xml
.rtf,application/rtf
.rtf,text/rtf
.rt,text/richtext
.rt,text/vnd.rn-realtext
.rtx,application/rtf
.rtx,text/richtext
.rv,video/vnd.rn-realvideo
.s3m,audio/s3m
.saf,application/vnd.yamaha.smaf-audio
.saveme,application/octet-stream
.sbk,application/x-tbook
.sbml,application/sbml+xml
.sc,application/vnd.ibm.secure-container
.scd,application/x-msschedule
.scm,application/vnd.lotus-screencam
.scm,application/x-lotusscreencam
.scm,text/x-script.guile
.scm,text/x-script.scheme
.scm,video/x-scm
.scq,application/scvp-cv-request
.scs,application/scvp-cv-response
.scurl,text/vnd.curl.scurl
.sda,application/vnd.stardivision.draw
.sdc,application/vnd.stardivision.calc
.sdd,application/vnd.stardivision.impress
.sdf,application/octet-stream
.sdkm,application/vnd.solent.sdkm+xml
.sdml,text/plain
.sdp,application/sdp
.sdp,application/x-sdp
.sdr,application/sounder
.sdw,application/vnd.stardivision.writer
.sea,application/sea
.sea,application/x-sea
.see,application/vnd.seemail
.seed,application/vnd.fdsn.seed
.sema,application/vnd.sema
.semd,application/vnd.semd
.semf,application/vnd.semf
.ser,application/java-serialized-object
.set,application/set
.setpay,application/set-payment-initiation
.setreg,application/set-registration-initiation
.sfd-hdstx,application/vnd.hydrostatix.sof-data
.sfs,application/vnd.spotfire.sfs
.sgl,application/vnd.stardivision.writer-global
.sgml,text/sgml
.sgml,text/x-sgml
.sgm,text/sgml
.sgm,text/x-sgml
.sh,application/x-bsh
.sh,application/x-sh
.sh,application/x-shar
.shar,application/x-bsh
.shar,application/x-shar
.shf,application/shf+xml
.sh,text/x-script.sh
.shtml,text/html
.shtml,text/x-server-parsed-html
.sid,audio/x-psid
.sis,application/vnd.symbian.install
.sit,application/x-sit
.sit,application/x-stuffit
.sitx,application/x-stuffitx
.skd,application/x-koan
.skm,application/x-koan
.skp,application/vnd.koan
.skp,application/x-koan
.skt,application/x-koan
.sl,application/x-seelogo
.sldm,application/vnd.ms-powerpoint.slide.macroenabled.12
.sldx,application/vnd.openxmlformats-officedocument.presentationml.slide
.slt,application/vnd.epson.salt
.sm,application/vnd.stepmania.stepchart
.smf,application/vnd.stardivision.math
.smi,application/smil
.smi,application/smil+xml
.smil,application/smil
.snd,audio/basic
.snd,audio/x-adpcm
.snf,application/x-font-snf
.sol,application/solids
.spc,application/x-pkcs7-certificates
.spc,text/x-speech
.spf,application/vnd.yamaha.smaf-phrase
.spl,application/futuresplash
.spl,application/x-futuresplash
.spot,text/vnd.in3d.spot
.spp,application/scvp-vp-response
.spq,application/scvp-vp-request
.spr,application/x-sprite
.sprite,application/x-sprite
.src,application/x-wais-source
.srt,text/srt
.sru,application/sru+xml
.srx,application/sparql-results+xml
.sse,application/vnd.kodak-descriptor
.ssf,application/vnd.epson.ssf
.ssi,text/x-server-parsed-html
.ssm,application/streamingmedia
.ssml,application/ssml+xml
.sst,application/vnd.ms-pki.certstore
.st,application/vnd.sailingtracker.track
.stc,application/vnd.sun.xml.calc.template
.std,application/vnd.sun.xml.draw.template
.step,application/step
.s,text/x-asm
.stf,application/vnd.wt.stf
.sti,application/vnd.sun.xml.impress.template
.stk,application/hyperstudio
.stl,application/sla
.stl,application/vnd.ms-pki.stl
.stl,application/x-navistyle
.stp,application/step
.str,application/vnd.pg.format
.stw,application/vnd.sun.xml.writer.template
.sub,image/vnd.dvb.subtitle
.sus,application/vnd.sus-calendar
.sv4cpio,application/x-sv4cpio
.sv4crc,application/x-sv4crc
.svc,application/vnd.dvb.service
.svd,application/vnd.svd
.svf,image/vnd.dwg
.svf,image/x-dwg
.svg,image/svg+xml
.svr,application/x-world
.svr,x-world/x-svr
.swf,application/x-shockwave-flash
.swi,application/vnd.aristanetworks.swi
.sxc,application/vnd.sun.xml.calc
.sxd,application/vnd.sun.xml.draw
.sxg,application/vnd.sun.xml.writer.global
.sxi,application/vnd.sun.xml.impress
.sxm,application/vnd.sun.xml.math
.sxw,application/vnd.sun.xml.writer
.talk,text/x-speech
.tao,application/vnd.tao.intent-module-archive
.t,application/x-troff
.tar,application/x-tar
.tbk,application/toolbook
.tbk,application/x-tbook
.tcap,application/vnd.3gpp2.tcap
.tcl,application/x-tcl
.tcl,text/x-script.tcl
.tcsh,text/x-script.tcsh
.teacher,application/vnd.smart.teacher
.tei,application/tei+xml
.tex,application/x-tex
.texi,application/x-texinfo
.texinfo,application/x-texinfo
.text,text/plain
.tfi,application/thraud+xml
.tfm,application/x-tex-tfm
.tgz,application/gnutar
.tgz,application/x-compressed
.thmx,application/vnd.ms-officetheme
.tiff,image/tiff
.tif,image/tiff
.tmo,application/vnd.tmobile-livetv
.torrent,application/x-bittorrent
.tpl,application/vnd.groove-tool-template
.tpt,application/vnd.trid.tpt
.tra,application/vnd.trueapp
.tr,application/x-troff
.trm,application/x-msterminal
.tsd,application/timestamped-data
.tsi,audio/tsp-audio
.tsp,application/dsptype
.tsp,audio/tsplayer
.tsv,text/tab-separated-values
.t,text/troff
.ttf,application/x-font-ttf
.ttl,text/turtle
.turbot,image/florian
.twd,application/vnd.simtech-mindmapper
.txd,application/vnd.genomatix.tuxedo
.txf,application/vnd.mobius.txf
.txt,text/plain
.ufd,application/vnd.ufdl
.uil,text/x-uil
.umj,application/vnd.umajin
.unis,text/uri-list
.uni,text/uri-list
.unityweb,application/vnd.unity
.unv,application/i-deas
.uoml,application/vnd.uoml+xml
.uris,text/uri-list
.uri,text/uri-list
.ustar,application/x-ustar
.ustar,multipart/x-ustar
.utz,application/vnd.uiq.theme
.uu,application/octet-stream
.uue,text/x-uuencode
.uu,text/x-uuencode
.uva,audio/vnd.dece.audio
.uvh,video/vnd.dece.hd
.uvi,image/vnd.dece.graphic
.uvm,video/vnd.dece.mobile
.uvp,video/vnd.dece.pd
.uvs,video/vnd.dece.sd
.uvu,video/vnd.uvvu.mp4
.uvv,video/vnd.dece.video
.vcd,application/x-cdlink
.vcf,text/x-vcard
.vcg,application/vnd.groove-vcard
.vcs,text/x-vcalendar
.vcx,application/vnd.vcx
.vda,application/vda
.vdo,video/vdo
.vew,application/groupwise
.vis,application/vnd.visionary
.vivo,video/vivo
.vivo,video/vnd.vivo
.viv,video/vivo
.viv,video/vnd.vivo
.vmd,application/vocaltec-media-desc
.vmf,application/vocaltec-media-file
.vob,video/dvd
.voc,audio/voc
.voc,audio/x-voc
.vos,video/vosaic
.vox,audio/voxware
.vqe,audio/x-twinvq-plugin
.vqf,audio/x-twinvq
.vql,audio/x-twinvq-plugin
.vrml,application/x-vrml
.vrml,model/vrml
.vrml,x-world/x-vrml
.vrt,x-world/x-vrt
.vsd,application/vnd.visio
.vsd,application/x-visio
.vsf,application/vnd.vsf
.vst,application/x-visio
.vsw,application/x-visio
.vtt,text/vtt
.vtu,model/vnd.vtu
.vxml,application/voicexml+xml
.w60,application/wordperfect6.0
.w61,application/wordperfect6.1
.w6w,application/msword
.wad,application/x-doom
.war,application/zip
.wasm,application/wasm
.wav,audio/wav
.wax,audio/x-ms-wax
.wb1,application/x-qpro
.wbmp,image/vnd.wap.wbmp
.wbs,application/vnd.criticaltools.wbs+xml
.wbxml,application/vnd.wap.wbxml
.weba,audio/webm
.web,application/vnd.xara
.webm,video/webm
.webp,image/webp
.wg,application/vnd.pmi.widget
.wgt,application/widget
.wiz,application/msword
.wk1,application/x-123
.wma,audio/x-ms-wma
.wmd,application/x-ms-wmd
.wmf,application/x-msmetafile
.wmf,windows/metafile
.wmlc,application/vnd.wap.wmlc
.wmlsc,application/vnd.wap.wmlscriptc
.wmls,text/vnd.wap.wmlscript
.wml,text/vnd.wap.wml
.wm,video/x-ms-wm
.wmv,video/x-ms-wmv
.wmx,video/x-ms-wmx
.wmz,application/x-ms-wmz
.woff,application/x-font-woff
.word,application/msword
.wp5,application/wordperfect
.wp5,application/wordperfect6.0
.wp6,application/wordperfect
.wp,application/wordperfect
.wpd,application/vnd.wordperfect
.wpd,application/wordperfect
.wpd,application/x-wpwin
.wpl,application/vnd.ms-wpl
.wps,application/vnd.ms-works
.wq1,application/x-lotus
.wqd,application/vnd.wqd
.wri,application/mswrite
.wri,application/x-mswrite
.wri,application/x-wri
.wrl,application/x-world
.wrl,model/vrml
.wrl,x-world/x-vrml
.wrz,model/vrml
.wrz,x-world/x-vrml
.wsc,text/scriplet
.wsdl,application/wsdl+xml
.wspolicy,application/wspolicy+xml
.wsrc,application/x-wais-source
.wtb,application/vnd.webturbo
.wtk,application/x-wintalk
.wvx,video/x-ms-wvx
.x3d,application/vnd.hzn-3d-crossword
.xap,application/x-silverlight-app
.xar,application/vnd.xara
.xbap,application/x-ms-xbap
.xbd,application/vnd.fujixerox.docuworks.binder
.xbm,image/xbm
.xbm,image/x-xbitmap
.xbm,image/x-xbm
.xdf,application/xcap-diff+xml
.xdm,application/vnd.syncml.dm+xml
.xdp,application/vnd.adobe.xdp+xml
.xdr,video/x-amt-demorun
.xdssc,application/dssc+xml
.xdw,application/vnd.fujixerox.docuworks
.xenc,application/xenc+xml
.xer,application/patch-ops-error+xml
.xfdf,application/vnd.adobe.xfdf
.xfdl,application/vnd.xfdl
.xgz,xgl/drawing
.xhtml,application/xhtml+xml
.xif,image/vnd.xiff
.xla,application/excel
.xla,application/x-excel
.xla,application/x-msexcel
.xlam,application/vnd.ms-excel.addin.macroenabled.12
.xl,application/excel
.xlb,application/excel
.xlb,application/vnd.ms-excel
.xlb,application/x-excel
.xlc,application/excel
.xlc,application/vnd.ms-excel
.xlc,application/x-excel
.xld,application/excel
.xld,application/x-excel
.xlk,application/excel
.xlk,application/x-excel
.xll,application/excel
.xll,application/vnd.ms-excel
.xll,application/x-excel
.xlm,application/excel
.xlm,application/vnd.ms-excel
.xlm,application/x-excel
.xls,application/excel
.xls,application/vnd.ms-excel
.xls,application/x-excel
.xls,application/x-msexcel
.xlsb,application/vnd.ms-excel.sheet.binary.macroenabled.12
.xlsm,application/vnd.ms-excel.sheet.macroenabled.12
.xlsx,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
.xlt,application/excel
.xlt,application/x-excel
.xltm,application/vnd.ms-excel.template.macroenabled.12
.xltx,application/vnd.openxmlformats-officedocument.spreadsheetml.template
.xlv,application/excel
.xlv,application/x-excel
.xlw,application/excel
.xlw,application/vnd.ms-excel
.xlw,application/x-excel
.xlw,application/x-msexcel
.xm,audio/xm
.xml,application/xml
.xml,text/xml
.xmz,xgl/movie
.xo,application/vnd.olpc-sugar
.xop,application/xop+xml
.xpi,application/x-xpinstall
.xpix,application/x-vnd.ls-xpix
.xpm,image/xpm
.xpm,image/x-xpixmap
.x-png,image/png
.xpr,application/vnd.is-xpr
.xps,application/vnd.ms-xpsdocument
.xpw,application/vnd.intercon.formnet
.xslt,application/xslt+xml
.xsm,application/vnd.syncml+xml
.xspf,application/xspf+xml
.xsr,video/x-amt-showrun
.xul,application/vnd.mozilla.xul+xml
.xwd,image/x-xwd
.xwd,image/x-xwindowdump
.xyz,chemical/x-pdb
.xyz,chemical/x-xyz
.xz,application/x-xz
.yaml,text/yaml
.yang,application/yang
.yin,application/yin+xml
.z,application/x-compress
.z,application/x-compressed
.zaz,application/vnd.zzazz.deck+xml
.zip,application/zip
.zip,application/x-compressed
.zip,application/x-zip-compressed
.zip,multipart/x-zip
.zir,application/vnd.zul
.zmm,application/vnd.handheld-entertainment+xml
.zoo,application/octet-stream
.zsh,text/x-script.zsh
"""

internal fun loadMimes(): List<Pair<String, ContentType>> {
    return rawMimes.lineSequence().mapNotNull {
        val line = it.trim()
        if (line.isEmpty()) return@mapNotNull null

        val index = line.indexOf(',')
        val extension = line.substring(0, index)
        val mime = line.substring(index + 1)

        extension.removePrefix(".").toLowerCasePreservingASCIIRules() to mime.toContentType()
    }.toList()
}

@ThreadLocal
internal val mimes: List<Pair<String, ContentType>> by lazy { loadMimes() }
