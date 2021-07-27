package id.ac.farz.mfarishanifw_uas.fragment.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.farz.mfarishanifw_uas.DetailBeritaActivity;
import id.ac.farz.mfarishanifw_uas.R;
import id.ac.farz.mfarishanifw_uas.adapter.AdapterBerita;
import id.ac.farz.mfarishanifw_uas.model.BeritaModel;

public class NewsFragment extends Fragment {
    ListView listView;
    AdapterBerita adapterBerita;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        adapterBerita = new AdapterBerita(getActivity(), R.layout.item_berita_layout);
        listView.setAdapter(adapterBerita);
        loadDataList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BeritaModel model = (BeritaModel) parent.getAdapter().getItem(position);

                Intent intent =new Intent(getActivity(), DetailBeritaActivity.class);
                intent.putExtra("image", model.getImage());
                intent.putExtra("judul", model.getJudulBerita());
                intent.putExtra("isi",model.getIsiBerita());
                startActivity(intent);

            }
        });

        registerForContextMenu(listView);
    }

    void loadDataList() {
        String[] image = new String[]{"https://akcdn.detik.net.id/community/media/visual/2020/10/26/adv-transpark.jpeg?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2020/10/27/kapolsek-mampang-prapatan-kompol-sujarwo-didampingi-kanit-reskrim-iptu-sigit-dan-kasubag-humas-akprita-1_169.jpeg?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2020/10/27/temuan-indikasi-kehidupan-di-venus-sebuah-kesalahan-pengukuran.jpeg?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2020/10/16/adv-unilever.jpeg?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2020/11/02/didakwa-terima-suap-rp-21-m-brigjen-prasetijo-salam-jempol_169.jpeg?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2020/11/10/gunung-merapi_169.jpeg?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2020/11/09/adv.png?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2020/08/06/pasien-di-rumah-sakit_169.jpeg?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2015/11/23/96726934-3545-4853-a72c-44db815ca2fe_169.jpg?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2020/02/04/b23f7152-cf59-472e-8585-fc7f49829131_169.jpeg?w=700&q=90",};

        String[] judul = new String[]{"Vaksin COVID-19 Ditemukan, Saatnya Berburu Investasi Properti",
                "Alarm Berbunyi, 2 Pria Ini Gagal Curi Motor di Mampang Jaksel",
                "Kisah Wargiyem, Pertahankan Usaha Saat Pandemi Lewat Digitalisasi",
                "Brigjen Prasetijo Cecar Anak Buah soal Bakar Surat Jalan: Jangan Buat Fitnah",
                "Potensi Bahaya Erupsi Gunung Merapi di Kali Gendol",
                "Kredit New Normal bikin Cicilan Menjadi Super Ringan, Sudah Tahu?",
                "Andrea Iannone Dilarang Balapan 4 Tahun, Kariernya Terancam Habis",
                "Positif Corona, Pengantin Baru Asal Sragen dan Orang Tuanya Meninggal",
                "Kejar Sandal yang Hanyut, Bocah Bogor Tenggelam dan Tewas di Sungai",
                "Harga Minyak AS Naik 11% Gara-gara Vaksin Pfizer",};

        String[] isiBerita = new String[]{"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Jakarta -  Seiring dengan pandemi yang berkepanjangan, para pelaku usaha mikro, kecil dan menengah (UMKM) juga semakin kesulitan untuk mempertahankan usahanya. Tanpa adanya inovasi yang dilakukan, para #PenjagaWarga tersebut bisa saja harus kehilangan sumber penghasilan yang dimiliki.\n" +
                        "Namun, ada cerita inspiratif yang dibagikan oleh salah satu pelaku UMKM bernama Wargiyem. Wargiyem adalah seorang pemilik toko kelontong bernama 'Warung Eva' yang terletak di Pedongkelan, Jakarta Utara. Ia pun menceritakan perjuangannya berjualan bersama anak perempuannya yaitu Elis.\n" +
                        "\n" +
                        "\"Saya usaha sudah 20-30 tahun di sini. Dari nol sampai sekarang, dulu pas mulai karena suami gak bekerja jadi warung adalah sumber penghasilan keluarga,\" ungkap Wargiyem, dalam keterangan tertulis.\n" +
                        "\n" +
                        "Wargiyem menceritakan kesulitannya untuk berjualan di tengah pandemi. Kendala seperti mencari stok barang warungnya sering dialami, namun ia terus berjuang karena warung tersebut merupakan sumber penghasilan satu-satunya bagi keluarga.\n" +
                        "\n" +
                        "\"Sekolahin anak, makan, semuanya dari sini. Pokoknya untuk hidup saya gitu,\" ujarnya.\n" +
                        "\n" +
                        "Namun, Wargiyem menyatakan dirinya dapat bertahan setelah ia menggunakan teknologi untuk mencari stok barang lewat aplikasi bernama Sahabat Warung.\n" +
                        "\n" +
                        "Wargiyem juga menuturkan beberapa merek produk lebih dicari ketika orang-orang melakukan transaksi di warungnya. Dan untungnya produk-produk yang laris manis tersebut dapat ditemukan lewat aplikasi Sahabat Warung.\n" +
                        "\n" +
                        "\"Unilever adalah produk yang paling dicari. Dari dulu, dari mulai agen naik motor sampai sekarang online (jualannya),\" ujar Wargiyem.",
                "Jakarta - Kompol Jhony Andrijanto mengaku membakar surat jalan palsu Joko Soegiarto Tjandra alias Djoko Tjandra atas perintah Brigjen Prasetijo Utomo. Prasetijo mencecar Jhony terkait perintah membakar surat jalan palsu Djoko Tjandra itu.\n" +
                        "\"Ini soal bakar surat penting buat saya. Kamu bangga, ya, bakar barang bukti? Atau surat?,\" kata Prasetijo dalam persidangan di Pengadilan Negeri Jakarta Timur, Selasa (10/11/2020).\n" +
                        "\n" +
                        "Baca juga:\n" +
                        "Anak Buah Prasetijo Akui Bakar Surat Jalan: Saya Lakukan karena Perintah\n" +
                        "Jhony pun menjawab tidak bangga dengan perbuatannya. Prasetijo kembali menanyakan alasan Jhony membakar surat tersebut.\n" +
                        "\n" +
                        "\n" +
                        "\"Kirain bangga. Dari tadi kayanya bangga. Kamu sadar nggak kalau kamu yang bakar?,\" kata Prasetijo.\n" +
                        "\n" +
                        "\"Sadar,\" jawab Jhony.\n" +
                        "\n" +
                        "\"Kenapa dibakar?,\" kata Prasetijo.\n" +
                        "\n" +
                        "\"Perintah jenderal,\" ujar Jhony.\n" +
                        "\n" +
                        "Prasetijo kembali menanyakan dengan apa dia menyuruh Jhony membakat surat-surat tersebut.\n" +
                        "\n" +
                        "\"Menggunakan apa saya perintahkan?,\" tanya Prasetijo.\n" +
                        "\n" +
                        "\"Lewat WA (WhatsApp),\" kata Jhony.\n" +
                        "\n" +
                        "\"WA apa? HP apa? Saya nggak tahu,\" ujar Prasetiko.\n" +
                        "\n" +
                        "\"Telepon. HP jendral yang biasa hubungi saya,\" tutur Jhony.\n" +
                        "\n" +
                        "Prasetijo membantah dirinya menghubungi dan memerintahkan pembakaran dokumen pada Jhony. Prasetijo juga menyebut kesaksian Jhony sebagai fitnah.\n" +
                        "\n" +
                        "\"Saya tidak pernah menghubungi itu. Jangan kamu buat fitnah di sini,\" kata Prasetijo.\n" +
                        "\n" +
                        "\"Terima kasih Pak Jhony, Anda sudah bantu saya, atau terbalik saya bantu membina Anda di biro PPNS, terima kasih sudah jadi pengkhianat,\" pungkasnya.\n" +
                        "\n" +
                        "Sekadar informasi, Jhony berpangkat kompol dan bekerja sebagai anak buah dari Prasetijo saat menjabat Kepala Biro Koordinasi dan Pengawasan PPNS Bareskrim Polri. Nama Jhony sendiri tertera dalam surat jalan, surat COVID dan surat keterangan sehat.\n" +
                        "\n" +
                        "Jhony diketahui ikut mendampingi Prasetijo dalam perjalanan ke Pontianak menemui Djoko Tjandra. Jhony juga mengakui telah membakar surat-surat jalan tersebut setelah diperintahkan oleh Prasetijo.",
                "Sleman - BPPTKG menyebut bahaya erupsi Merapi mengarah ke Kali Gendol karena bukaan kawah ada di arah Kali Gendol. Selain itu deformasi saat ini dominan ke arah barat.\n" +
                        "PreviousNext\n" +
                        "Balai Penyelidikan dan Pengembangan Teknologi Kebencanaan Geologi (BPPTKG) menyebut potensi bahaya erupsi Gunung Merapi mengarah ke Kali Gendol.  ",
                "Jakarta - Virus Corona menyerang seluruh dunia, menciptakan kondisi new normal, membuat semua orang harus berhati-hati saat bepergian keluar rumah. Apalagi saat berada di kerumunan, virus rentan menyebar dari satu orang ke orang yang lain. Oleh karena itu, menjaga jarak di tempat umum atau social distancing, termasuk di transportasi umum menjadi hal yang wajib dilakukan.\n" +
                        "Walau bagaimanapun tetap saja, perjalanan dengan transportasi umum membuat tak nyaman, karena kerap merasa was-was akan sebaran virus yang tak kasat mata. Ditambah, di dalamnya banyak orang-orang yang tak dikenal.\n" +
                        "\n" +
                        "Melihat hal itu bepergian dengan menggunakan kendaraan atau mobil pribadi menjadi opsi paling aman selama pandemi.\n" +
                        "\n" +
                        "Namun di sisi lain keinginan beli mobil pribadi menjadi berat karena masalah ekonomi yang belum stabil. Bagaimana dengan DP-nya? Bagaimana dengan cicilannya? Mungkin pertanyaan-pertanyaan ini akan sering muncul di benak kita saat menimbang untuk membeli mobil pribadi di kondisi ini.\n" +
                        "\n" +
                        "Memahami kebutuhan masyarakat di masa pandemi ini, BCA Finance hadirkan program Mini for Max, yaitu kredit dengan DP rendah dan Cicilan ringan yang dapat dipercaya menjadi solusi memiliki mobil pribadi di tengah pandemi.\n" +
                        "\n" +
                        "Berikut untuk ilustrasi skema pembiayaannya.\n" +
                        "\n" +
                        "Misalkan kamu bayar DP 20% dari harga kendaraan, maka hutang kamu adalah 80% dari harga kendaraan. Tapi kamu tidak usah khawatir, yang dicicil hanya 30% saja selama 4 tahun, sisanya 50% dapat kamu lunasi setelahnya, atau kamu bisa cicil kembali selama 4 tahun, dengan bunga yang telah dijanjikan.\n" +
                        "\n" +
                        "Dengan skema pembayaran ini, calon pembeli bisa merasa nyaman karena akan mendapatkan cicilan yang jauh lebih ringan dibandingkan dengan cicilan reguler dengan tenor 4 tahun seperti biasa. Dengan cicilan yang ringan, calon pembeli bisa mengalokasikan sisa pendapatannya untuk kebutuhan lainnya.",
                "Jakarta - Andrea Iannone terancam habis kariernya di ajang MotoGP dan balap motor dunia. Dia baru saja divonis hukuman larangan membalap untuk periode empat tahun ke depan.\n" +
                        "Keputusan hukuman larangan membalap selama 4 tahun dijatuhkan oleh pengadilan banding olahraga (CAS). Sebelumnya, Iannone mengajukan banding karena dia dihukum larangan membalap selama 18 bulan.\n" +
                        "\n" +
                        "Alih-alih dapat potongan atau pengangkatan hukuman, rider asal Italia itu malah dapat hukuman lebih berat. Sanksi ini dijatuhkan pada Iannone karena dia kedapatan menggunakan obat-obatan yang dilarang yang dianggap sebagai doping.\n" +
                        "\n" +
                        "Baca juga: Curhat Andrea Iannone Jalani Hukuman Tak Boleh Balapan\n" +
                        "\"Andrea Iannone tidak mampu menunjukkan bukti-bukti yang meyakinkan bahwa pelanggaran yang dia lakukan terhadap aturan anti-doping terjadi tanpa sengaja,\" demikian pernyataan resmi CAS.\n" +
                        "\n" +
                        "\"Berdasarkan temuan yang didapat panel....pelanggaran aturan anti-doping yang terjadi pada Iannone dianggap sebagai disengaja untuk tujuan tertentu untuk mensiasati aturan anti-doping,\" demikian dikutip dari Crash.\n" +
                        "\n" +
                        "Iannone kedapatan mengonsumsi obat terlarang bernama Drostanolone pada MotoGP Malaysia, November 2019. Dia dinyatakan bersalah oleh FIM dan dijatuhi hukuman 18 bulan mulai 17 Desember 2019 sampai 16 Juni 2021.\n" +
                        "\n" +
                        "Hukuman ini membuat Aprilia harus mencari pebalap pengganti Iannone untuk mengarungi MotoGP 2021. Ada beberapa rider dengan statistik oke yang bisa dikontrak Aprilia. Termasuk Andrea Dovizioso, Cal Crutchlow, Jorge Lorenzo, dan and Bradley Smith.",
                "Sragen - L (28) pengantin baru asal Desa Wonorejo, Kecamatan Kalijambe, Sragen, meninggal dalam kondisi positif terpapar virus Corona atau COVID-19. Tragisnya, hanya berselang beberapa hari, kedua orang tua L menyusul meninggal juga akibat terpapar COVID-19.\n" +
                        "\"Yang bersangkutan positif COVID-19. Meninggal tanggal 5 November di RSUD Moewardi. Ibunya meninggal tanggal 6 November dan ayahnya meninggal Senin kemarin (9 November). Orang tuanya juga positif,\" ujar Kepala Dinas Kesehatan Kabupaten (DKK) Sragen Hargiyanto, dihubungi detikcom, Selasa (10/11/2020).\n" +
                        "\n" +
                        "Hargiyanto mengatakan, ibu L menjalani perawatan di RSUD Ngipang Solo. Sementara ayahnya dirawat di RSUD dr Soeratno Gemolong. Menurut Hargiyanto, kuat dugaan kedua orang tua tersebut terpapar Corona dari L.\n" +
                        "\n" +
                        "\n" +
                        "\"Dua-duanya kemungkinan terpapar dari anaknya. Sementara anaknya kemungkinan terpapar dari Jakarta karena bekerjanya di Jakarta,\" terangnya.",
                "Bogor - MF, bocah lelaki berusia 9 tahun, tenggelam dan tewas di Sungai Cikalang yang tak jauh dari rumahnya, Desa Pasir Muncang, Kecamatan Caringin, Kabupaten Bogor, Selasa (10/11/2020). Ia tenggelam saat berusaha mengejar sandalnya yang hanyut di sungai.\n" +
                        "Kasie Kedaruratan BPBD Kabupaten Bogor Muhamad Adam mengatakan kejadian tersebut berlangsung sekitar pukul 16.40 WIB. Saat itu, kata Adam, MF tengah bermain di jembatan bambu yang di bawahnya mengalir aliran Sungai Cikalang.\n" +
                        "\n" +
                        "\"Pada saat bermain itu, sandal korban terjatuh. Kemudian korban mengejar sandalnya. Korban jatuh ke sungai dan hanyut,\" kata Adam.",
                "Jakarta - Harga minyak mentah meroket pada Senin (9/11/2020). Harga minyak naik setelah adanya kabar mengenai vaksin Corona dari Pfizer.\n" +
                        "Seperti dikutip CNN, Selasa (10/11/2020), harga minyak mentah Amerika Serikat (AS) naik sampai 11% menjadi US$ 41,22 per barel karena Pfizer mengumumkan vaksinnya efektif menangkal Corona.\n" +
                        "\n" +
                        "Berita vaksin ini sangat penting bagi industri minyak. Sebab, pandemi membuat permintaan akan bahan bakar turun drastis.\n" +
                        "\n" +
                        "\n" +
                        "Di sisi lain, infeksi COVID-19 yang meningkat di AS dan Eropa membuat investor khawatir adanya pembatasan yang baru. Sebab, akan mengganggu pertumbuhan ekonomi yang berpengaruh pada permintaan minyak.\n" +
                        "\n" +
                        "\"Minyak naik seperti hal gila, menggabungkan titik-titik antara vaksin potensial dan rebound dalam pertumbuhan permintaan global,\" kata Matt Smith, direktur penelitian komoditas di ClipperData."};

        for(int i=0; i< image.length;i++){
            BeritaModel model = new BeritaModel();
            model.setImage(image[i]);
            model.setJudulBerita(judul[i]);
            model.setIsiBerita(isiBerita[i]);
            adapterBerita.add(model);
        }
        adapterBerita.notifyDataSetChanged();
    }

}