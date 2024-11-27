import './css/Homepage.css';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import img_upper from "../assets/img/homepage/main_illustration.png";
import img_1 from "../assets/img/homepage/1.png";
import img_2 from "../assets/img/homepage/2.png";
import img_3 from "../assets/img/homepage/3.png";
import img_4 from "../assets/img/homepage/4.png";
import img_5 from "../assets/img/homepage/5.png";
import img_6 from "../assets/img/homepage/6.png";
import img_7 from "../assets/img/homepage/7.png";
import purple_img1 from "../assets/img/homepage/2girlsAtMac.png";
import purple_img2 from "../assets/img/homepage/dudeAtWhiteboard.png";
import img_unten from "../assets/img/homepage/gruppenbild_unten.png";


export default function HomePage() {
    return (
        <div className="homepage">
            <section className="upper_section">
                <article className='upper_article'>
                    <h1>Hilf mit die Umwelt zu schützen</h1>
                    <p>
                        Abfälle bedrohen Vögel, Delfine und Co. Mehr als zehn Millionen Tonnen Abfälle gelangen jährlich in die  Ozeane. Sie kosten Abertausende Meerestiere das Leben. Seevögel verwechseln Plastik mit natürlicher Nahrung, Delfine verfangen sich in alten Fischernetzen. Hilf mit Müll zu reduzieren und trashnothing.
                    </p>
                    <Link to="/marktplatz">
                            <button className='button_starteJetzt'>Starte jetzt!</button>
                    </Link>
                </article>
                <article>
                    <img src={img_upper} className='img_upper' />
                    
                </article>
            </section>
            
            <h2 className='h2_eCommerce'>Lebe eCommerce mal anders</h2>

            <section className="Lebe_eCommerce">
                <article className="article_left">
                    <img src={img_1} className='img_article' />
                    <img src={img_2 } className='img_wierd_left' />
                    <h3 className='h3_article'>Verkaufen statt wegwerfen</h3>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                    </p>
                </article>
                <article className="article_mid">
                    <img className='img_article' src={img_3} alt="chair" />
                    <h3 className='h3_article'>Verschenke und Schütze</h3>
                    <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                    </p>
                </article>
                <article className="article_right">
                    <img className='img_article' src={img_4} alt="forest" />
                    <img className='img_wierd_right_top' src={img_7} alt="woman sitting on rock" />
                    <img className='img_wierd_right_mid' src={img_6} alt="woman sitting on rock" />
                    <img className='img_wierd_right_bottom' src={img_5} alt="woman sitting on rock" />
                    <h3 className='h3_article'>Der Umwelt zuliebe</h3>
                    <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                    </p>
                </article>
            </section>

            <section className="purple_section">
                <article className="ohne_limits_purple">
                    <img className='purple_img1' src={purple_img1} alt="2 Girls with Mac" />
                    <div className='div_purple1'>
                        <h3 className='h3_purple'>Ohne Limits</h3>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                        </p>
                        <Link to="/registration">
                            <button className='button_zurDoku'>Registriere dich jetzt!</button>
                    </Link>
                    </div>
                </article>
                <article className="Community_purple">
                    <div className='div_purple2'>
                        <h3 className='h3_purple'>Kenn deine Community</h3>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                        </p>
                        <Link to="/login">
                            <button className='button_zurDoku'>Melde dich jetzt an!</button>
                    </Link>
                    </div>
                    <img className='purple_img2' src={purple_img2} alt="Guy at Whiteboard" />
                </article>
            </section>

            <section className="last_Section">
                <h2>Von echten Menschen unterstützt</h2>
                <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                </p>
                <img className='img_unten' src={img_unten} alt="gruppenbild" />
            </section>
        </div>
    );
}