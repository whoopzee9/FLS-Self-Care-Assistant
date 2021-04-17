import React from 'react';
import styles from './test.module.css';

const Test = () => {
    return (
        <div className={styles.test}>
            <p className={styles.text}>test for module.css</p>
            <button className={styles.btn}>btn for test</button>
        </div>
    )
}

export default Test