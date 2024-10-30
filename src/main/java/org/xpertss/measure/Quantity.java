package org.xpertss.measure;

/**
 * This interface represents any type of quantitative properties or attributes
 * of a thing. Mass, time, distance, heat, and angular separation are among the
 * familiar examples of quantitative properties.
 * <p/>
 * Distinct quantities usually have different physical dimensions; although it is
 * not required nor necessary, for example {@link Torque} and {@link Energy} have
 * the same dimension but are of a different nature (vector for torque, scalar for
 * energy).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Quantity">Wikipedia: Quantity</a>
 * @see <a href="http://en.wikipedia.org/wiki/Dimensional_analysis">
 * Wikipedia: Dimensional Analysis</a>
 * @see <a href="http://en.wikipedia.org/wiki/Fundamental_unit">
 * Wikipedia: Fundamental Units</a>
 */
public interface Quantity<Q extends Quantity<Q>> {

    // TODO The spec has a ton of methods we do not have defined here
    // The spec might be overloading this with Measurement
}
